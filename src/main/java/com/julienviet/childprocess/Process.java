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

package com.julienviet.childprocess;

import com.julienviet.childprocess.impl.ProcessBuilderImpl;
import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.internal.ContextInternal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A process launched from this current process.
 * <p>
 * Please see the user manual for more detailed usage information.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@VertxGen
public interface Process {

  /**
   * @return the current process environment variables
   */
  static Map<String, String> env() {
    return new HashMap<>(System.getenv());
  }

  /**
   * Create a child process (not running) from this process, call {@link ProcessBuilder#start()} to start the process.
   *
   * @param vertx the vertx instance
   * @param command the command to run
   * @return the created child process
   */
  static ProcessBuilder create(Vertx vertx, String command) {
    return create(vertx, command, Collections.emptyList(), new ProcessOptions());
  }

  /**
   * Create a child process (not running) from this process, call {@link ProcessBuilder#start()} to start the process.
   *
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @return the created child process
   */
  static ProcessBuilder create(Vertx vertx, String command, List<String> args) {
    return create(vertx, command, args, new ProcessOptions());
  }

  /**
   * Create a child process (not running) from this process, call {@link ProcessBuilder#start()} to start the process.
   *
   * @param vertx the vertx instance
   * @param command the command to run
   * @param options the options to run the command
   * @return the created child process
   */
  static ProcessBuilder create(Vertx vertx, String command, ProcessOptions options) {
    return create(vertx, command, Collections.emptyList(), options);
  }

  /**
   * Create a child process (not running) from this process, call {@link ProcessBuilder#start()} to start the process.
   *
   * @param vertx the vertx instance
   * @param command the command to run
   * @param args list of string arguments
   * @param options the options to run the command
   * @return the created child process
   */
  static ProcessBuilder create(Vertx vertx, String command, List<String> args, ProcessOptions options) {
    return new ProcessBuilderImpl((ContextInternal) vertx.getOrCreateContext(), command, args, options);
  }

  /**
   * Set the handler to be called when the process exits, the handler will be called with the
   * process status code value.
   *
   * @param handler the handler
   * @return a reference to this, so the API can be used fluently
   */
  @Fluent
  Process exitHandler(Handler<Integer> handler);

  /**
   * @return the process PID or null if the process is not running
   */
  Integer pid();

  /**
   * @return the process stdin stream
   */
  @CacheReturn
  StreamOutput stdin();

  /**
   * @return the process stdout stream
   */
  @CacheReturn
  StreamInput stdout();

  /**
   * @return the process stderr stream
   */
  @CacheReturn
  StreamInput stderr();

  /**
   * Terminates the process in a graceful manner.
   * <p>
   * On a POSIX OS, it sends the {@code SIGTERM}.
   */
  default void kill() {
    kill(false);
  }


  /**
   * Terminates the process.
   * <p>
   * If {@code force} is {@code false}, the process will be terminated gracefully (i.e. its shutdown logic will
   * be allowed to execute), assuming the OS supports such behavior. Note that the process may not actually
   * terminate, as its cleanup logic may fail or it may choose to ignore the termination request. If a guarantee
   * of termination is required, call this method with force equal to true instead.
   * <p>
   * If {@code force} is {@code true}, the process is guaranteed to terminate, but whether it is terminated
   * gracefully or not is OS-dependent. Note that it may take the OS a moment to terminate the process, so
   * {@link #isRunning()} may return {@code true} for a brief period after calling this method.
   * <p>
   * On a POSIX OS, it sends the {@code SIGTERM} or {@code SIGKILL} signals.
   *
   * @param force if true is passed, the process will be forcibly killed
   */
  void kill(boolean force);

  /**
   * Tests whether or not the process is still running or has exited.
   */
  boolean isRunning();

}

