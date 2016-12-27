package io.vertx.ext.childprocess;

import com.zaxxer.nuprocess.NuProcessBuilder;
import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.childprocess.impl.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface ChildProcess {


  static void spawn(Vertx vertx, List<String> commands, Handler<ChildProcess> handler) {
    NuProcessBuilder builder = new NuProcessBuilder(new ChildProcessImpl(vertx.getOrCreateContext(), handler), commands);
    builder.start();
  }

  static void spawn(Vertx vertx, List<String> commands, ProcessOptions options, Handler<ChildProcess> handler) {
    NuProcessBuilder builder;
    if (options.getEnv() == null) {
      builder = new NuProcessBuilder(commands);
    } else {
      Map<String, String> env = new HashMap<>();
      options.getEnv().forEach(entry -> {
        if (entry.getValue() != null) {
          env.put(entry.getKey(), entry.getValue().toString());
        }
      });
      builder = new NuProcessBuilder(commands, env);
    }
    if (options.getCwd() != null) {
      builder.setCwd(new File(options.getCwd()).toPath());
    }
    builder.setProcessListener(new ChildProcessImpl(vertx.getOrCreateContext(), handler));
    builder.start();
  }

  @Fluent
  ChildProcess exitHandler(Handler<Integer> handler);

  @CacheReturn
  StreamOutput stdin();

  @CacheReturn
  StreamInput stdout();

  @CacheReturn
  StreamInput stderr();

  /**
   * Terminates the process.
   * <p>
   * If {@code force} is {@code false}, the process will be terminated gracefully (i.e. its shutdown logic will
   * be allowed to execute), assuming the OS supports such behavior. Note that the process may not actually
   * terminate, as its cleanup logic may fail or it may choose to ignore the termination request. If a guarantee
   * of termination is required, call this method with force equal to true instead.
   * <p>
   * If {@code force} is {@code true}, the process is guaranteed to terminate, but whether it is terminated
   * gracefully or not is OS-dependent. Note that it may take the OS a moment to terminate the process, so
   * {@link #isRunning()} may return {@code true} for a brief period after calling this method.
   *
   * @param force if true is passed, the process will be forcibly killed
   */
  void destroy(boolean force);

  /**
   * Tests whether or not the process is still running or has exited.
   */
  boolean isRunning();

}

