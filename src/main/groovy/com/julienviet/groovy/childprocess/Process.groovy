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
import java.util.List
import com.julienviet.childprocess.ProcessOptions
import java.util.Map
import io.vertx.groovy.core.Vertx
import io.vertx.core.Handler
/**
 * A process launched from this current process.
 * <p>
 * Call the static {@link com.julienviet.groovy.childprocess.Process#spawn} to spawn child processes from the current process.
 * <p>
 * Please see the user manual for more detailed usage information.
*/
@CompileStatic
public class Process {
  private final def com.julienviet.childprocess.Process delegate;
  public Process(Object delegate) {
    this.delegate = (com.julienviet.childprocess.Process) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * @return the current process environment variables
   */
  public static Map<String, String> env() {
    def ret = com.julienviet.childprocess.Process.env();
    return ret;
  }
  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.spawn(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command), com.julienviet.groovy.childprocess.Process.class);
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
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.spawn(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command, args != null ? (List)args.collect({it}) : null), com.julienviet.groovy.childprocess.Process.class);
    return ret;
  }
  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param options the options to run the command (see <a href="../../../../../../cheatsheet/ProcessOptions.html">ProcessOptions</a>)
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command, Map<String, Object> options) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.spawn(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command, options != null ? new com.julienviet.childprocess.ProcessOptions(io.vertx.lang.groovy.InternalHelper.toJsonObject(options)) : null), com.julienviet.groovy.childprocess.Process.class);
    return ret;
  }
  /**
   * Create and start a child process from this process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command (see <a href="../../../../../../cheatsheet/ProcessOptions.html">ProcessOptions</a>)
   * @return the process
   */
  public static Process spawn(Vertx vertx, String command, List<String> args, Map<String, Object> options) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.spawn(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command, args != null ? (List)args.collect({it}) : null, options != null ? new com.julienviet.childprocess.ProcessOptions(io.vertx.lang.groovy.InternalHelper.toJsonObject(options)) : null), com.julienviet.groovy.childprocess.Process.class);
    return ret;
  }
  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.groovy.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command), com.julienviet.groovy.childprocess.Process.class);
    return ret;
  }
  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.groovy.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, List<String> args) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command, args != null ? (List)args.collect({it}) : null), com.julienviet.groovy.childprocess.Process.class);
    return ret;
  }
  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.groovy.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param options the options to run the command (see <a href="../../../../../../cheatsheet/ProcessOptions.html">ProcessOptions</a>)
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, Map<String, Object> options) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command, options != null ? new com.julienviet.childprocess.ProcessOptions(io.vertx.lang.groovy.InternalHelper.toJsonObject(options)) : null), com.julienviet.groovy.childprocess.Process.class);
    return ret;
  }
  /**
   * Create a child process (not running) from this process, call {@link com.julienviet.groovy.childprocess.Process#start} to start the process.
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command (see <a href="../../../../../../cheatsheet/ProcessOptions.html">ProcessOptions</a>)
   * @return the created child process
   */
  public static Process create(Vertx vertx, String command, List<String> args, Map<String, Object> options) {
    def ret = InternalHelper.safeCreate(com.julienviet.childprocess.Process.create(vertx != null ? (io.vertx.core.Vertx)vertx.getDelegate() : null, command, args != null ? (List)args.collect({it}) : null, options != null ? new com.julienviet.childprocess.ProcessOptions(io.vertx.lang.groovy.InternalHelper.toJsonObject(options)) : null), com.julienviet.groovy.childprocess.Process.class);
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
    delegate.start(handler != null ? new Handler<com.julienviet.childprocess.Process>(){
      public void handle(com.julienviet.childprocess.Process event) {
        handler.handle(InternalHelper.safeCreate(event, com.julienviet.groovy.childprocess.Process.class));
      }
    } : null);
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
    def ret = delegate.pid();
    return ret;
  }
  /**
   * @return the process stdin stream
   */
  public StreamOutput stdin() {
    if (cached_0 != null) {
      return cached_0;
    }
    def ret = InternalHelper.safeCreate(delegate.stdin(), com.julienviet.groovy.childprocess.StreamOutput.class);
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
    def ret = InternalHelper.safeCreate(delegate.stdout(), com.julienviet.groovy.childprocess.StreamInput.class);
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
    def ret = InternalHelper.safeCreate(delegate.stderr(), com.julienviet.groovy.childprocess.StreamInput.class);
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
   * {@link com.julienviet.groovy.childprocess.Process#isRunning} may return <code>true</code> for a brief period after calling this method.
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
    def ret = delegate.isRunning();
    return ret;
  }
  private StreamOutput cached_0;
  private StreamInput cached_1;
  private StreamInput cached_2;
}
