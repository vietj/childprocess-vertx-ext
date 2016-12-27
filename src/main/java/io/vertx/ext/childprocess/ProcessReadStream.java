package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface ProcessReadStream {

  @Fluent
  ProcessReadStream exceptionHandler(Handler<Throwable> handler);

  @Fluent
  ProcessReadStream handler(Handler<Buffer> handler);

  @Fluent
  ProcessReadStream endHandler(Handler<Void> handler);
}
