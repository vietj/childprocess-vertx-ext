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

package com.julienviet.childprocess.impl;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import com.julienviet.childprocess.StreamInput;
import io.vertx.core.impl.ContextInternal;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class ProcessStreamInput implements StreamInput {

  private final ContextInternal context;
  private Handler<Buffer> dataHandler;
  private Handler<Void> endHandler;

  ProcessStreamInput(ContextInternal context) {
    this.context = context;
  }

  synchronized void write(Buffer buffer) {
    sendBuffer(buffer);
  }

  void close() {
    Handler<Void> handler = endHandler;
    if (handler != null) {
      context.emit(handler);
    }
  }

  private void sendBuffer(Buffer buffer) {
    Handler<Buffer> handler = dataHandler;
    if (handler != null) {
      context.emit(buffer, handler);
    }
  }

  @Override
  public StreamInput exceptionHandler(Handler<Throwable> handler) {
    return this;
  }

  @Override
  public StreamInput handler(Handler<Buffer> handler) {
    this.dataHandler = handler;
    return this;
  }

  @Override
  public StreamInput endHandler(Handler<Void> handler) {
    this.endHandler = handler;
    return this;
  }
}
