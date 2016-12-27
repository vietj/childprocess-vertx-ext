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
import java.util.Map;
import io.vertx.rxjava.core.Vertx;
import io.vertx.core.Handler;

/**
 * A process launched from this current process.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link io.vertx.ext.childprocess.Process original} non RX-ified interface using Vert.x codegen.
 */

public class Process {

  final io.vertx.ext.childprocess.Process delegate;

  public Process(io.vertx.ext.childprocess.Process delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  /**
   * @return the current process environment variables
   */
  public static Map<String,String> env() { 
    Map<String,String> ret = io.vertx.ext.childprocess.Process.env();
    return ret;
  }

  /**
   * Spawn a child process from this process.
   * @param vertx the vertx instance
   * @param command 
   * @return 
   */
  public static Process spawn(Vertx vertx, String command) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command));
    return ret;
  }

  public static Process spawn(Vertx vertx, String command, List<String> args) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command, args));
    return ret;
  }

  public static Process spawn(Vertx vertx, String command, ProcessOptions options) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command, options));
    return ret;
  }

  /**
   * Spawn a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command, List<String> args, ProcessOptions options) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command, args, options));
    return ret;
  }

  public static Process create(Vertx vertx, String command) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command));
    return ret;
  }

  public static Process create(Vertx vertx, String command, List<String> args) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command, args));
    return ret;
  }

  public static Process create(Vertx vertx, String command, ProcessOptions options) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command, options));
    return ret;
  }

  /**
   * Create a child process (not running) from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, List<String> args, ProcessOptions options) { 
    Process ret = Process.newInstance(io.vertx.ext.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command, args, options));
    return ret;
  }

  public void start() { 
    delegate.start();
  }

  public void start(Handler<Process> handler) { 
    delegate.start(new Handler<io.vertx.ext.childprocess.Process>() {
      public void handle(io.vertx.ext.childprocess.Process event) {
        handler.handle(Process.newInstance(event));
      }
    });
  }

  /**
   * Set the handler to be called when the process exits, the handler will be called with the
   * process status code value.
   * @param handler the handler
   * @return a reference to this, so the API can be used fluently
   */
  public Process exitHandler(Handler<Integer> handler) { 
    delegate.exitHandler(handler);
    return this;
  }

  /**
   * @return the process stdin stream
   */
  public StreamOutput stdin() { 
    if (cached_0 != null) {
      return cached_0;
    }
    StreamOutput ret = StreamOutput.newInstance(delegate.stdin());
    cached_0 = ret;
    return ret;
  }

  /**
   * @return the process stdout stream
   */
  public StreamInput stdout() { 
    if (cached_1 != null) {
      return cached_1;
    }
    StreamInput ret = StreamInput.newInstance(delegate.stdout());
    cached_1 = ret;
    return ret;
  }

  /**
   * @return the process stderr stream
   */
  public StreamInput stderr() { 
    if (cached_2 != null) {
      return cached_2;
    }
    StreamInput ret = StreamInput.newInstance(delegate.stderr());
    cached_2 = ret;
    return ret;
  }

  /**
   * Terminates the process.
   * <p>
   * If <code>force</code> is <code>false</code>, the process will be terminated gracefully (i.e. its shutdown logic will
   * be allowed to execute), assuming the OS supports such behavior. Note that the process may not actually
   * terminate, as its cleanup logic may fail or it may choose to ignore the termination request. If a guarantee
   * of termination is required, call this method with force equal to true instead.
   * <p>
   * If <code>force</code> is <code>true</code>, the process is guaranteed to terminate, but whether it is terminated
   * gracefully or not is OS-dependent. Note that it may take the OS a moment to terminate the process, so
   * {@link io.vertx.rxjava.ext.childprocess.Process#isRunning} may return <code>true</code> for a brief period after calling this method.
   * @param force if true is passed, the process will be forcibly killed
   */
  public void destroy(boolean force) { 
    delegate.destroy(force);
  }

  /**
   * Tests whether or not the process is still running or has exited.
   * @return 
   */
  public boolean isRunning() { 
    boolean ret = delegate.isRunning();
    return ret;
  }

  private StreamOutput cached_0;
  private StreamInput cached_1;
  private StreamInput cached_2;

  public static Process newInstance(io.vertx.ext.childprocess.Process arg) {
    return arg != null ? new Process(arg) : null;
  }
}
