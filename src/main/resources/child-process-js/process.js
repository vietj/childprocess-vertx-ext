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

/** @module child-process-js/process */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');
var StreamOutput = require('child-process-js/stream_output');
var StreamInput = require('child-process-js/stream_input');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JProcess = com.julienviet.childprocess.Process;
var ProcessOptions = com.julienviet.childprocess.ProcessOptions;

/**
 A process launched from this current process.

 @class
*/
var Process = function(j_val) {

  var j_process = j_val;
  var that = this;

  /**
   Start the process.

   @public
   @param handler {function} the handler to be called when the process has started 
   */
  this.start = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_process["start()"]();
    }  else if (__args.length === 1 && typeof __args[0] === 'function') {
      j_process["start(io.vertx.core.Handler)"](function(jVal) {
      __args[0](utils.convReturnVertxGen(jVal, Process));
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Set the handler to be called when the process exits, the handler will be called with the
   process status code value.

   @public
   @param handler {function} the handler 
   @return {Process} a reference to this, so the API can be used fluently
   */
  this.exitHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_process["exitHandler(io.vertx.core.Handler)"](function(jVal) {
      handler(jVal);
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {number} the process PID or null if the process is not running
   */
  this.pid = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_process["pid()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {StreamOutput} the process stdin stream
   */
  this.stdin = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (that.cachedstdin == null) {
        that.cachedstdin = utils.convReturnVertxGen(j_process["stdin()"](), StreamOutput);
      }
      return that.cachedstdin;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {StreamInput} the process stdout stream
   */
  this.stdout = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (that.cachedstdout == null) {
        that.cachedstdout = utils.convReturnVertxGen(j_process["stdout()"](), StreamInput);
      }
      return that.cachedstdout;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {StreamInput} the process stderr stream
   */
  this.stderr = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (that.cachedstderr == null) {
        that.cachedstderr = utils.convReturnVertxGen(j_process["stderr()"](), StreamInput);
      }
      return that.cachedstderr;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Terminates the process.
   <p>
   If <code>force</code> is <code>false</code>, the process will be terminated gracefully (i.e. its shutdown logic will
   be allowed to execute), assuming the OS supports such behavior. Note that the process may not actually
   terminate, as its cleanup logic may fail or it may choose to ignore the termination request. If a guarantee
   of termination is required, call this method with force equal to true instead.
   <p>
   If <code>force</code> is <code>true</code>, the process is guaranteed to terminate, but whether it is terminated
   gracefully or not is OS-dependent. Note that it may take the OS a moment to terminate the process, so
   {@link Process#isRunning} may return <code>true</code> for a brief period after calling this method.
   <p>
   On a POSIX OS, it sends the <code>SIGTERM</code> or <code>SIGKILL</code> signals.

   @public
   @param force {boolean} if true is passed, the process will be forcibly killed 
   */
  this.kill = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_process["kill()"]();
    }  else if (__args.length === 1 && typeof __args[0] ==='boolean') {
      j_process["kill(boolean)"](__args[0]);
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Tests whether or not the process is still running or has exited.

   @public

   @return {boolean}
   */
  this.isRunning = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return j_process["isRunning()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_process;
};

/**

 @memberof module:child-process-js/process

 @return {Array.<string>} the current process environment variables
 */
Process.env = function() {
  var __args = arguments;
  if (__args.length === 0) {
    return utils.convReturnMap(JProcess["env()"]());
  } else throw new TypeError('function invoked with invalid arguments');
};

/**
 Create and start a child process from this process.

 @memberof module:child-process-js/process
 @param vertx {Vertx} the vertx instance 
 @param command {string} the command to run 
 @param args {Array.<string>} list of string arguments 
 @param options {Object} the options to run the command 
 @return {Process} the process
 */
Process.spawn = function() {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JProcess["spawn(io.vertx.core.Vertx,java.lang.String)"](__args[0]._jdel, __args[1]), Process);
  }else if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string' && typeof __args[2] === 'object' && __args[2] instanceof Array) {
    return utils.convReturnVertxGen(JProcess["spawn(io.vertx.core.Vertx,java.lang.String,java.util.List)"](__args[0]._jdel, __args[1], utils.convParamListBasicOther(__args[2])), Process);
  }else if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null)) {
    return utils.convReturnVertxGen(JProcess["spawn(io.vertx.core.Vertx,java.lang.String,com.julienviet.childprocess.ProcessOptions)"](__args[0]._jdel, __args[1], __args[2] != null ? new ProcessOptions(new JsonObject(JSON.stringify(__args[2]))) : null), Process);
  }else if (__args.length === 4 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string' && typeof __args[2] === 'object' && __args[2] instanceof Array && (typeof __args[3] === 'object' && __args[3] != null)) {
    return utils.convReturnVertxGen(JProcess["spawn(io.vertx.core.Vertx,java.lang.String,java.util.List,com.julienviet.childprocess.ProcessOptions)"](__args[0]._jdel, __args[1], utils.convParamListBasicOther(__args[2]), __args[3] != null ? new ProcessOptions(new JsonObject(JSON.stringify(__args[3]))) : null), Process);
  } else throw new TypeError('function invoked with invalid arguments');
};

/**
 Create a child process (not running) from this process, call {@link Process#start} to start the process.

 @memberof module:child-process-js/process
 @param vertx {Vertx} the vertx instance 
 @param command {string} the command to run 
 @param args {Array.<string>} list of string arguments 
 @param options {Object} the options to run the command 
 @return {Process} the created child process
 */
Process.create = function() {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JProcess["create(io.vertx.core.Vertx,java.lang.String)"](__args[0]._jdel, __args[1]), Process);
  }else if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string' && typeof __args[2] === 'object' && __args[2] instanceof Array) {
    return utils.convReturnVertxGen(JProcess["create(io.vertx.core.Vertx,java.lang.String,java.util.List)"](__args[0]._jdel, __args[1], utils.convParamListBasicOther(__args[2])), Process);
  }else if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string' && (typeof __args[2] === 'object' && __args[2] != null)) {
    return utils.convReturnVertxGen(JProcess["create(io.vertx.core.Vertx,java.lang.String,com.julienviet.childprocess.ProcessOptions)"](__args[0]._jdel, __args[1], __args[2] != null ? new ProcessOptions(new JsonObject(JSON.stringify(__args[2]))) : null), Process);
  }else if (__args.length === 4 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string' && typeof __args[2] === 'object' && __args[2] instanceof Array && (typeof __args[3] === 'object' && __args[3] != null)) {
    return utils.convReturnVertxGen(JProcess["create(io.vertx.core.Vertx,java.lang.String,java.util.List,com.julienviet.childprocess.ProcessOptions)"](__args[0]._jdel, __args[1], utils.convParamListBasicOther(__args[2]), __args[3] != null ? new ProcessOptions(new JsonObject(JSON.stringify(__args[3]))) : null), Process);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = Process;