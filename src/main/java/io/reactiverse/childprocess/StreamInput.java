/*
 * Copyright (C) 2016 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.reactiverse.childprocess;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.StreamBase;

/**
 * The input of a process: a stream of {@link Buffer buffers}.
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
