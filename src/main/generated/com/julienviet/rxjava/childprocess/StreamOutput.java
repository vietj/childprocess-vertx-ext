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

package com.julienviet.rxjava.childprocess;

import java.util.Map;
import rx.Observable;
import io.vertx.rxjava.core.buffer.Buffer;
import io.vertx.rxjava.core.streams.WriteStream;
import io.vertx.core.Handler;

/**
 * The output of a process:  a stream of .
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link com.julienviet.childprocess.StreamOutput original} non RX-ified interface using Vert.x codegen.
 */

public class StreamOutput implements WriteStream<Buffer> {

  final com.julienviet.childprocess.StreamOutput delegate;

  public StreamOutput(com.julienviet.childprocess.StreamOutput delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public void end(Buffer t) { 
    delegate.end((io.vertx.core.buffer.Buffer)t.getDelegate());
  }

  public boolean writeQueueFull() { 
    boolean ret = delegate.writeQueueFull();
    return ret;
  }

  public StreamOutput exceptionHandler(Handler<Throwable> handler) { 
    ((io.vertx.core.streams.WriteStream) delegate).exceptionHandler(handler);
    return this;
  }

  public StreamOutput write(Buffer buffer) { 
    ((io.vertx.core.streams.WriteStream) delegate).write((io.vertx.core.buffer.Buffer)buffer.getDelegate());
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
   * Close the stream.
   */
  public void close() { 
    delegate.close();
  }


  public static StreamOutput newInstance(com.julienviet.childprocess.StreamOutput arg) {
    return arg != null ? new StreamOutput(arg) : null;
  }
}
