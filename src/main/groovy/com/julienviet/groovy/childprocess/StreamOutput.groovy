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
import io.vertx.groovy.core.streams.WriteStream
import io.vertx.core.Handler
/**
 * The output of a process.
*/
@CompileStatic
public class StreamOutput implements WriteStream<Buffer> {
  private final def com.julienviet.childprocess.StreamOutput delegate;
  public StreamOutput(Object delegate) {
    this.delegate = (com.julienviet.childprocess.StreamOutput) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public void end(Buffer t) {
    ((io.vertx.core.streams.WriteStream) delegate).end(t != null ? (io.vertx.core.buffer.Buffer)t.getDelegate() : null);
  }
  public boolean writeQueueFull() {
    def ret = ((io.vertx.core.streams.WriteStream) delegate).writeQueueFull();
    return ret;
  }
  public StreamOutput exceptionHandler(Handler<Throwable> handler) {
    ((io.vertx.core.streams.WriteStream) delegate).exceptionHandler(handler);
    return this;
  }
  public StreamOutput write(Buffer buffer) {
    ((io.vertx.core.streams.WriteStream) delegate).write(buffer != null ? (io.vertx.core.buffer.Buffer)buffer.getDelegate() : null);
    return this;
  }
  public StreamOutput setWriteQueueMaxSize(int i) {
    ((io.vertx.core.streams.WriteStream) delegate).setWriteQueueMaxSize(i);
    return this;
  }
  public StreamOutput drainHandler(Handler<Void> handler) {
    ((io.vertx.core.streams.WriteStream) delegate).drainHandler(handler);
    return this;
  }
  /**
   * Calls {code close()}.
   */
  public void end() {
    ((io.vertx.core.streams.WriteStream) delegate).end();
  }
  /**
   * Closes the stream.
   */
  public void close() {
    delegate.close();
  }
}
