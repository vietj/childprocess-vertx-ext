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
    if (options.getEnv() != null) {
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
  ProcessWriteStream stdin();

  @CacheReturn
  ProcessReadStream stdout();

  @CacheReturn
  ProcessReadStream stderr();

  void destroy(boolean force);

  boolean isRunning();

}

