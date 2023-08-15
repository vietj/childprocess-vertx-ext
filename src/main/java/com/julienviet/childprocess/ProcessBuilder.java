package com.julienviet.childprocess;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;

@VertxGen
public interface ProcessBuilder {

  /**
   * Start the process.
   */
  Future<Void> start();

  /**
   * Set the handler to be called when the process starts.
   *
   * @param handler the handler
   * @return a reference to this, so the API can be used fluently
   */
  @Fluent
  ProcessBuilder startHandler(Handler<Process> handler);

}
