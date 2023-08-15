/*
 * Copyright (C) 2016 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.julienviet.childprocess;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.test.core.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@RunWith(VertxUnitRunner.class)
public class SpawnTest {

  Vertx vertx;

  @Before
  public void before() {
    vertx = Vertx.vertx();
  }

  @After
  public void after(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testExitCode(TestContext testContext) {
    Async async = testContext.async();
    Context context = vertx.getOrCreateContext();
    context.runOnContext(v -> {
      ProcessBuilder processBuilder = Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "ExitCode"));
      processBuilder.startHandler(process -> {
        process.exitHandler(code -> {
          testContext.assertEquals(context, Vertx.currentContext());
          testContext.assertEquals(25, code);
          async.complete();
        });
      }).start();
    });
  }

  @Ignore
  @Test
  public void testStdin(TestContext context) {
    Buffer chunk = Buffer.buffer(TestUtils.randomAlphaString(1024));
    Async async = context.async(2);
    AtomicInteger status = new AtomicInteger();
    Process.create(vertx, "read").startHandler(process -> {
      StreamOutput stdin = process.stdin();
      while (!stdin.writeQueueFull()) {
        stdin.write(chunk);
      }
      stdin
        .close()
        .onComplete(context.asyncAssertSuccess(v -> async.countDown()));
      process.exitHandler(code -> {
        context.assertEquals(0, status.getAndIncrement());
        async.countDown();
      });
    }).start();
  }

  @Test
  public void testStdout(TestContext context) {
    testStream(context, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "EchoStdout", "the_echoed_value"), Process::stdout);
  }

  @Test
  public void testStderr(TestContext context) {
    testStream(context, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "EchoStderr", "the_echoed_value"), Process::stderr);
  }

  private void testStream(TestContext testContext, String command, List<String> args, Function<Process, StreamInput> streamExtractor) {
    Async async = testContext.async();
    AtomicInteger status = new AtomicInteger();
    Context context = vertx.getOrCreateContext();
    context.runOnContext(v -> {
      ProcessBuilder processBuilder = Process.create(vertx, command, args);
      processBuilder.startHandler(process -> {
        StreamInput stream = streamExtractor.apply(process);
        stream.handler(buf -> {
          testContext.assertEquals(context, Vertx.currentContext());
          testContext.assertEquals(0, status.getAndIncrement());
          testContext.assertEquals("the_echoed_value", buf.toString());
        });
        stream.endHandler(d -> {
          testContext.assertEquals(context, Vertx.currentContext());
          testContext.assertEquals(1, status.getAndIncrement());
        });
        process.exitHandler(code -> {
          testContext.assertEquals(2, status.getAndIncrement());
          async.complete();
        });
      }).start();
    });
  }


  @Test
  public void testDrainStdin(TestContext testContext) throws IOException {
    Path tmp = Files.createTempFile("test", ".shared");
    tmp.toFile().deleteOnExit();
    Async async = testContext.async();
    AtomicInteger status = new AtomicInteger();
    Context context = vertx.getOrCreateContext();
    context.runOnContext(v -> {
      ProcessBuilder processBuilder = Process.create(vertx, "/usr/bin/java", Arrays.asList(/*"-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005",*/ "-cp", "target/test-classes", Main.class.getName(), tmp.toFile().getAbsolutePath()));
      processBuilder.startHandler(process -> {
        StreamOutput stdin = process.stdin();
        stdin.setWriteQueueMaxSize(8000);
        process.exitHandler(code -> {
          testContext.assertEquals(1, status.getAndIncrement());
          testContext.assertEquals(0, code);
          async.complete();
        });
        int size = 0;
        while (!stdin.writeQueueFull()) {
          Buffer buf = Buffer.buffer("hello");
          stdin.write(buf);
          size += buf.length();
        }
        testContext.assertTrue(size > 8000);
        try {
          Files.write(tmp, new byte[1]);
        } catch (IOException e) {
          testContext.fail(e);
          return;
        }
        stdin.drainHandler(v2 -> {
          testContext.assertEquals(context, Vertx.currentContext());
          testContext.assertEquals(0, status.getAndIncrement());
          stdin.write(Buffer.buffer(new byte[]{4})); // EOF
        });
      }).start();
    });
  }

  @Ignore
  @Test
  public void testCat(TestContext context) {
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    Process.create(vertx, "/bin/cat").startHandler(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      StreamOutput stdin = process.stdin();
      stdin.write(Buffer.buffer("hello"));
      stdin.close();
      process.exitHandler(code -> {
        context.assertEquals("hello", out.toString());
        context.assertEquals(0, status.getAndIncrement());
        async.complete();
      });
    }).start();
  }

  @Test
  public void testCwd(TestContext context) {
    Async async = context.async();
    String cwd = new File(new File("target"), "test-classes").getAbsolutePath();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("PrintCwd"), new ProcessOptions().setCwd(cwd)).startHandler(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals(cwd, new File(out.toString()).getAbsolutePath());
        async.complete();
      });
    }).start();
  }

  @Test
  public void testEnv(TestContext context) {
    Async async = context.async();
    ProcessOptions options = new ProcessOptions();
    options.getEnv().put("foo", "foo_value");
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "PrintEnv", "foo"), options).startHandler(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals("foo_value", out.toString());
        async.complete();
      });
    }).start();
  }

  @Test
  public void testDefaultEnv(TestContext context) {
    Map.Entry<String, String> entry = System.getenv().entrySet().iterator().next();
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "PrintEnv", entry.getKey()), new ProcessOptions()).startHandler(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals(entry.getValue(), out.toString());
        async.complete();
      });
    }).start();
  }

  @Test
  public void testStdoutLongSequence(TestContext context) {
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "StdoutLongSequence")).startHandler(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        StringBuilder expected = new StringBuilder();
        for (int i = 0;i < 100000;i++) {
          expected.append(i);
        }
        context.assertEquals(expected.toString(), out.toString());
        async.complete();
      });
    }).start();
  }

  @Test
  public void testDestroy(TestContext context) throws Exception {
    Async async = context.async();
    StringBuilder sb = new StringBuilder();
    AtomicReference<Process> p = new AtomicReference<>();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "Shutdown")).startHandler(process -> {
      p.set(process);
      process.stdout().handler(sb::append);
    }).start();
    long now = System.currentTimeMillis();
    while (sb.length() < 2) {
      context.assertTrue(System.currentTimeMillis() - now < 10000);
      Thread.sleep(1);
    }
    context.assertEquals("ok", sb.toString());
    sb.setLength(0);
    Process process = p.get();
    process.exitHandler(status -> {
      context.assertEquals("exited", sb.toString());
      async.complete();
    });
    process.kill(false);
  }

  @Test
  public void testDestroyForce(TestContext context) throws Exception {
    Async async = context.async();
    StringBuilder sb = new StringBuilder();
    AtomicReference<Process> p = new AtomicReference<>();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "Shutdown")).startHandler(process -> {
      p.set(process);
      process.stdout().handler(sb::append);
    }).start();
    long now = System.currentTimeMillis();
    while (sb.length() < 2) {
      context.assertTrue(System.currentTimeMillis() - now < 10000);
      Thread.sleep(1);
    }
    context.assertEquals("ok", sb.toString());
    sb.setLength(0);
    Process process = p.get();
    process.exitHandler(status -> {
      context.assertEquals("", sb.toString());
      async.complete();
    });
    process.kill(true);
  }

  @Test
  public void testFoo(TestContext context) throws Exception {
    Process.create(vertx, "/does/not/exists").startHandler(process -> {
      context.fail();
    }).start().onComplete(context.asyncAssertFailure(err -> {

    }));
  }
}
