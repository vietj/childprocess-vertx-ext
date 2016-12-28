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
import java.util.List;
import com.julienviet.childprocess.ProcessOptions;
import java.util.Map;
import io.vertx.rxjava.core.Vertx;
import io.vertx.core.Handler;

/**
 * A process launched from this current process.
 * <p>
 * Call the static {@link com.julienviet.rxjava.childprocess.Process#spawn} to spawn child processes from the current process.
 * <p>
 * Please see the user manual for more detailed usage information.
 *
 * <p/>
 * NOTE: This class has been automatically generated from the {@link com.julienviet.childprocess.Process original} non RX-ified interface using Vert.x codegen.
 */

public class Process {

  final com.julienviet.childprocess.Process delegate;

  public Process(com.julienviet.childprocess.Process delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  /**
   * @return the current process environment variables
   */
  public static Map<String,String> env() { 
    Map<String,String> ret = com.julienviet.childprocess.Process.env();
    return ret;
  }

  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command));
    return ret;
  }

  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command, List<String> args) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command, args));
    return ret;
  }

  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param options the options to run the command
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command, ProcessOptions options) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command, options));
    return ret;
  }

  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command, List<String> args, ProcessOptions options) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.spawn((io.vertx.core.Vertx)vertx.getDelegate(), command, args, options));
    return ret;
  }

  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.rxjava.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command));
    return ret;
  }

  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.rxjava.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, List<String> args) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command, args));
    return ret;
  }

  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.rxjava.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param options the options to run the command
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, ProcessOptions options) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command, options));
    return ret;
  }

  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.rxjava.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, List<String> args, ProcessOptions options) { 
    Process ret = Process.newInstance(com.julienviet.childprocess.Process.create((io.vertx.core.Vertx)vertx.getDelegate(), command, args, options));
    return ret;
  }

  /**
   * Start the process.
   */
  public void start() { 
    delegate.start();
  }

  /**
   * Start the process.
   * @param handler the handler to be called when the process has started
   */
  public void start(Handler<Process> handler) { 
    delegate.start(new Handler<com.julienviet.childprocess.Process>() {
      public void handle(com.julienviet.childprocess.Process event) {
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
   * @return the process PID or null if the process is not running
   */
  public Integer pid() { 
    Integer ret = delegate.pid();
    return ret;
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
   * Terminates the process in a graceful manner.
   * <p>
   * On a POSIX OS, it sends the <code>SIGTERM</code>.
   */
  public void kill() { 
    delegate.kill();
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
   * {@link com.julienviet.rxjava.childprocess.Process#isRunning} may return <code>true</code> for a brief period after calling this method.
   * <p>
   * On a POSIX OS, it sends the <code>SIGTERM</code> or <code>SIGKILL</code> signals.
   * @param force if true is passed, the process will be forcibly killed
   */
  public void kill(boolean force) { 
    delegate.kill(force);
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

  public static Process newInstance(com.julienviet.childprocess.Process arg) {
    return arg != null ? new Process(arg) : null;
  }
}
