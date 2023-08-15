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

package com.julienviet.childprocess;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;

/**
 * The output of a process:  a stream of {@link Buffer buffers}.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface StreamOutput extends WriteStream<Buffer> {

  @Override
  StreamOutput exceptionHandler(Handler<Throwable> handler);

  @Override
  Future<Void> write(Buffer buffer);

  @Override
  StreamOutput setWriteQueueMaxSize(int i);

  @Override
  StreamOutput drainHandler(Handler<Void> handler);

  /**
   * Calls {code close()}.
   */
  @Override
  default Future<Void> end() {
    return close().mapEmpty();
  }

  /**
   * Close the stream.
   */
  Future<Void> close();
}
