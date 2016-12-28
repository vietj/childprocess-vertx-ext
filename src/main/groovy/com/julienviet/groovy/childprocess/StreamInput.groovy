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

package com.julienviet.groovy.childprocess;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.groovy.core.buffer.Buffer
import io.vertx.groovy.core.streams.StreamBase
import io.vertx.core.Handler
/**
 * The input of a process.
*/
@CompileStatic
public class StreamInput implements StreamBase {
  private final def com.julienviet.childprocess.StreamInput delegate;
  public StreamInput(Object delegate) {
    this.delegate = (com.julienviet.childprocess.StreamInput) delegate;
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
    ((io.vertx.core.streams.StreamBase) delegate).exceptionHandler(handler);
    return this;
  }
  /**
   * Set a buffer handler. As bytes are read, the handler will be called with the data.
   * @param handler 
   * @return a reference to this, so the API can be used fluently
   */
  public StreamInput handler(Handler<Buffer> handler) {
    delegate.handler(handler != null ? new Handler<io.vertx.core.buffer.Buffer>(){
      public void handle(io.vertx.core.buffer.Buffer event) {
        handler.handle(InternalHelper.safeCreate(event, io.vertx.groovy.core.buffer.Buffer.class));
      }
    } : null);
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
}
