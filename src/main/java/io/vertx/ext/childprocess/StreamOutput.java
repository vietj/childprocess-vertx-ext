package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface StreamOutput extends WriteStream<Buffer> {

  @Override
  StreamOutput exceptionHandler(Handler<Throwable> handler);

  @Override
  StreamOutput write(Buffer buffer);

  @Override
  StreamOutput setWriteQueueMaxSize(int i);

  @Override
  StreamOutput drainHandler(Handler<Void> handler);

  @Override
  default void end() {
    close();
  }

  void close();
}
