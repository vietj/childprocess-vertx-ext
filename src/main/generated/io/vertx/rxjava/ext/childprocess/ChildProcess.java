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
import io.vertx.ext.childprocess.ProcessOptions;
import java.util.List;
import io.vertx.rxjava.core.Vertx;
import io.vertx.core.Handler;

/**
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link io.vertx.ext.childprocess.ChildProcess original} non RX-ified interface using Vert.x codegen.
 */

public class ChildProcess {

  final io.vertx.ext.childprocess.ChildProcess delegate;

  public ChildProcess(io.vertx.ext.childprocess.ChildProcess delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  public static void spawn(Vertx vertx, List<String> commands, Handler<ChildProcess> handler) { 
    io.vertx.ext.childprocess.ChildProcess.spawn((io.vertx.core.Vertx)vertx.getDelegate(), commands, new Handler<io.vertx.ext.childprocess.ChildProcess>() {
      public void handle(io.vertx.ext.childprocess.ChildProcess event) {
        handler.handle(ChildProcess.newInstance(event));
      }
    });
  }

  public static void spawn(Vertx vertx, List<String> commands, ProcessOptions options, Handler<ChildProcess> handler) { 
    io.vertx.ext.childprocess.ChildProcess.spawn((io.vertx.core.Vertx)vertx.getDelegate(), commands, options, new Handler<io.vertx.ext.childprocess.ChildProcess>() {
      public void handle(io.vertx.ext.childprocess.ChildProcess event) {
        handler.handle(ChildProcess.newInstance(event));
      }
    });
  }

  public ChildProcess exitHandler(Handler<Integer> handler) { 
    delegate.exitHandler(handler);
    return this;
  }

  public StreamOutput stdin() { 
    if (cached_0 != null) {
      return cached_0;
    }
    StreamOutput ret = StreamOutput.newInstance(delegate.stdin());
    cached_0 = ret;
    return ret;
  }

  public StreamInput stdout() { 
    if (cached_1 != null) {
      return cached_1;
    }
    StreamInput ret = StreamInput.newInstance(delegate.stdout());
    cached_1 = ret;
    return ret;
  }

  public StreamInput stderr() { 
    if (cached_2 != null) {
      return cached_2;
    }
    StreamInput ret = StreamInput.newInstance(delegate.stderr());
    cached_2 = ret;
    return ret;
  }

  public void destroy(boolean force) { 
    delegate.destroy(force);
  }

  public boolean isRunning() { 
    boolean ret = delegate.isRunning();
    return ret;
  }

  private StreamOutput cached_0;
  private StreamInput cached_1;
  private StreamInput cached_2;

  public static ChildProcess newInstance(io.vertx.ext.childprocess.ChildProcess arg) {
    return arg != null ? new ChildProcess(arg) : null;
  }
}
