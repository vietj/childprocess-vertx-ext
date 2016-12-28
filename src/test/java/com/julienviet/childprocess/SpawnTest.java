package com.julienviet.childprocess;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
      Process process = Process.spawn(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "ExitCode"));
      process.exitHandler(code -> {
        testContext.assertEquals(context, Vertx.currentContext());
        testContext.assertEquals(25, code);
        async.complete();
      });
    });
  }

  @Test
  public void testStdin(TestContext context) {
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    Process.create(vertx, "read").start(process -> {
      StreamOutput stdin = process.stdin();
      stdin.write(Buffer.buffer("hello"));
      stdin.close();
      process.exitHandler(code -> {
        context.assertEquals(0, status.getAndIncrement());
        async.complete();
      });
    });
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
      Process process = Process.spawn(vertx, command, args);
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
      Process process = Process.spawn(vertx, "/usr/bin/java", Arrays.asList(/*"-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005",*/ "-cp", "target/test-classes", Main.class.getName(), tmp.toFile().getAbsolutePath()));
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
    });
  }

  @Test
  public void testCat(TestContext context) {
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    Process.create(vertx, "/bin/cat").start(process -> {
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
    });
  }

  @Test
  public void testCwd(TestContext context) {
    Async async = context.async();
    String cwd = new File(new File("target"), "test-classes").getAbsolutePath();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("PrintCwd"), new ProcessOptions().setCwd(cwd)).start(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals(cwd, new File(out.toString()).getAbsolutePath());
        async.complete();
      });
    });
  }

  @Test
  public void testEnv(TestContext context) {
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "PrintEnv", "foo"), new ProcessOptions().setEnv(Collections.singletonMap("foo", "foo_value"))).start(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals("foo_value", out.toString());
        async.complete();
      });
    });
  }

  @Test
  public void testEmptyEnv(TestContext context) {
    Map.Entry<String, String> entry = System.getenv().entrySet().iterator().next();
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "PrintEnv", entry.getKey()), new ProcessOptions().setEnv(new HashMap<>())).start(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals("", out.toString());
        async.complete();
      });
    });
  }

  @Test
  public void testNullEnv(TestContext context) {
    Map.Entry<String, String> entry = System.getenv().entrySet().iterator().next();
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "PrintEnv", entry.getKey()), new ProcessOptions().setEnv(new HashMap<>())).start(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals("", out.toString());
        async.complete();
      });
    });
  }

  @Test
  public void testDefaultEnv(TestContext context) {
    Map.Entry<String, String> entry = System.getenv().entrySet().iterator().next();
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "PrintEnv", entry.getKey()), new ProcessOptions()).start(process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, code);
        context.assertEquals(entry.getValue(), out.toString());
        async.complete();
      });
    });
  }

  @Test
  public void testStdoutLongSequence(TestContext context) {
    Async async = context.async();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "StdoutLongSequence")).start(process -> {
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
    });
  }

  @Test
  public void testDestroy(TestContext context) throws Exception {
    Async async = context.async();
    StringBuilder sb = new StringBuilder();
    AtomicReference<Process> p = new AtomicReference<>();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "Shutdown")).start(process -> {
      p.set(process);
      process.stdout().handler(sb::append);
    });
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
    process.destroy(false);
  }

  @Test
  public void testDestroyForce(TestContext context) throws Exception {
    Async async = context.async();
    StringBuilder sb = new StringBuilder();
    AtomicReference<Process> p = new AtomicReference<>();
    Process.create(vertx, "/usr/bin/java", Arrays.asList("-cp", "target/test-classes", "Shutdown")).start(process -> {
      p.set(process);
      process.stdout().handler(sb::append);
    });
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
    process.destroy(true);
  }

  @Test
  public void testFoo(TestContext context) throws Exception {
    Async async = context.async();
    Process.create(vertx, "/does/not/exists").start(process -> {
      process.exitHandler(code -> {
        context.assertTrue(code < 0);
        async.complete();
      });
    });
  }
}
