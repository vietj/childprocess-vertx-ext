package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface StreamInput {

  @Fluent
  StreamInput exceptionHandler(Handler<Throwable> handler);

  @Fluent
  StreamInput handler(Handler<Buffer> handler);

  @Fluent
  StreamInput endHandler(Handler<Void> handler);
}
