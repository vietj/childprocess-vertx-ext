package com.julienviet.childprocess;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.StreamBase;

/**
 * The input of a process.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface StreamInput extends StreamBase {

  /**
   * Set an exception handler on the read stream.
   *
   * @param handler the exception handler
   * @return a reference to this, so the API can be used fluently
   */
  @Fluent
  StreamInput exceptionHandler(Handler<Throwable> handler);

  /**
   * Set a buffer handler. As bytes are read, the handler will be called with the data.
   *
   * @return a reference to this, so the API can be used fluently
   */
  @Fluent
  StreamInput handler(Handler<Buffer> handler);

  /**
   * Set an end handler. Once the stream has ended, and there is no more data to be read, this handler will be called.
   *
   * @return a reference to this, so the API can be used fluently
   */
  @Fluent
  StreamInput endHandler(Handler<Void> handler);
}
