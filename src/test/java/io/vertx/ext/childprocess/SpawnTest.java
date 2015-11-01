package io.vertx.ext.childprocess;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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
  public void testStdin(TestContext context) {
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    ChildProcess.spawn(vertx, Arrays.asList("read"), process -> {
      ProcessWriteStream stdin = process.stdin();
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
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    ChildProcess.spawn(vertx, Arrays.asList("/bin/echo", "the_echoed_value"), process -> {
      ProcessReadStream stdout = process.stdout();
      stdout.handler(buf -> {
        context.assertEquals(0, status.getAndIncrement());
        context.assertEquals("the_echoed_value\n", buf.toString());
      });
      stdout.endHandler(d -> {
        context.assertEquals(1, status.getAndIncrement());
      });
      process.exitHandler(code -> {
        context.assertEquals(2, status.getAndIncrement());
        async.complete();
      });
    });
  }


  @Test
  public void testDrainStdin(TestContext context) throws IOException {
    Path tmp = Files.createTempFile("test", ".shared");
    tmp.toFile().deleteOnExit();
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    ChildProcess.spawn(vertx, Arrays.asList("/usr/bin/java", /*"-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005",*/ "-cp", "target/test-classes", "io.vertx.ext.childprocess.Main", tmp.toFile().getAbsolutePath()), process -> {
      ProcessWriteStream stdin = process.stdin();
      stdin.setWriteQueueMaxSize(8000);
      process.exitHandler(code -> {
        context.assertEquals(1, status.getAndIncrement());
        context.assertEquals(0, code);
        async.complete();
      });
      int size = 0;
      while (!stdin.writeQueueFull()) {
        Buffer buf = Buffer.buffer("hello");
        stdin.write(buf);
        size += buf.length();
      }
      context.assertTrue(size > 8000);
      try {
        Files.write(tmp, new byte[1]);
      } catch (IOException e) {
        context.fail(e);
        return;
      }
      stdin.drainHandler(v -> {
        context.assertEquals(0, status.getAndIncrement());
        stdin.write(Buffer.buffer(new byte[]{4})); // EOF
      });
    });
  }

  @Test
  public void testCat(TestContext context) {
    Async async = context.async();
    AtomicInteger status = new AtomicInteger();
    ChildProcess.spawn(vertx, Arrays.asList("/bin/cat"), process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      ProcessWriteStream stdin = process.stdin();
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
    AtomicInteger status = new AtomicInteger();
    String cwd = new File(new File("target"), "test-classes").getAbsolutePath();
    ChildProcess.spawn(vertx, Arrays.asList("/usr/bin/java", "PrintCwd"), new ProcessOptions().setCwd(cwd), process -> {
      Buffer out = Buffer.buffer();
      process.stdout().handler(out::appendBuffer);
      process.exitHandler(code -> {
        context.assertEquals(0, status.getAndIncrement());
        context.assertEquals(cwd, new File(out.toString()).getAbsolutePath());
        async.complete();
      });
    });
  }
}
