package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface ProcessWriteStream extends WriteStream<Buffer> {

  @Override
  ProcessWriteStream exceptionHandler(Handler<Throwable> handler);

  @Override
  ProcessWriteStream write(Buffer buffer);

  @Override
  ProcessWriteStream setWriteQueueMaxSize(int i);

  @Override
  ProcessWriteStream drainHandler(Handler<Void> handler);

  void close();
}
