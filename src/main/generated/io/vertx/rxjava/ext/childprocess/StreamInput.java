/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.rxjava.ext.childprocess;

import java.util.Map;
import rx.Observable;
import io.vertx.rxjava.core.buffer.Buffer;
import io.vertx.rxjava.core.streams.StreamBase;
import io.vertx.core.Handler;

/**
 * The input of a process.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link io.vertx.ext.childprocess.StreamInput original} non RX-ified interface using Vert.x codegen.
 */

public class StreamInput implements StreamBase {

  final io.vertx.ext.childprocess.StreamInput delegate;

  public StreamInput(io.vertx.ext.childprocess.StreamInput delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  /**
   * Set an exception handler on the read stream.
   * @param handler the exception handler
   * @return a reference to this, so the API can be used fluently
   */
  public StreamInput exceptionHandler(Handler<Throwable> handler) { 
    ((io.vertx.ext.childprocess.StreamInput) delegate).exceptionHandler(handler);
    return this;
  }

  /**
   * Set a buffer handler. As bytes are read, the handler will be called with the data.
   * @param handler 
   * @return a reference to this, so the API can be used fluently
   */
  public StreamInput handler(Handler<Buffer> handler) { 
    delegate.handler(new Handler<io.vertx.core.buffer.Buffer>() {
      public void handle(io.vertx.core.buffer.Buffer event) {
        handler.handle(Buffer.newInstance(event));
      }
    });
    return this;
  }

  /**
   * Set an end handler. Once the stream has ended, and there is no more data to be read, this handler will be called.
   * @param handler 
   * @return a reference to this, so the API can be used fluently
   */
  public StreamInput endHandler(Handler<Void> handler) { 
    delegate.endHandler(handler);
    return this;
  }


  public static StreamInput newInstance(io.vertx.ext.childprocess.StreamInput arg) {
    return arg != null ? new StreamInput(arg) : null;
  }
}
