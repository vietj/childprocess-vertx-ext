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
import io.vertx.core.Handler;

/**
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link io.vertx.ext.childprocess.ProcessReadStream original} non RX-ified interface using Vert.x codegen.
 */

public class ProcessReadStream {

  final io.vertx.ext.childprocess.ProcessReadStream delegate;

  public ProcessReadStream(io.vertx.ext.childprocess.ProcessReadStream delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public ProcessReadStream exceptionHandler(Handler<Throwable> handler) { 
    delegate.exceptionHandler(handler);
    return this;
  }

  public ProcessReadStream handler(Handler<Buffer> handler) { 
    delegate.handler(new Handler<io.vertx.core.buffer.Buffer>() {
      public void handle(io.vertx.core.buffer.Buffer event) {
        handler.handle(Buffer.newInstance(event));
      }
    });
    return this;
  }

  public ProcessReadStream endHandler(Handler<Void> handler) { 
    delegate.endHandler(handler);
    return this;
  }


  public static ProcessReadStream newInstance(io.vertx.ext.childprocess.ProcessReadStream arg) {
    return arg != null ? new ProcessReadStream(arg) : null;
  }
}
