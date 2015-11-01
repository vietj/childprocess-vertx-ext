package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface ProcessReadStream extends ReadStream<Buffer> {

  @Override
  ProcessReadStream exceptionHandler(Handler<Throwable> handler);

  @Override
  ProcessReadStream handler(Handler<Buffer> handler);

  @Override
  ProcessReadStream pause();

  @Override
  ProcessReadStream resume();

  @Override
  ProcessReadStream endHandler(Handler<Void> handler);
}
