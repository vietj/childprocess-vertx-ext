package com.julienviet.childprocess;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;

/**
 * The output of a process.
 *
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

  /**
   * Calls {code close()}.
   */
  @Override
  default void end() {
    close();
  }

  /**
   * Closes the stream.
   */
  void close();
}
