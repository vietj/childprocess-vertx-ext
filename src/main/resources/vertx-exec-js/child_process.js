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

/** @module vertx-exec-js/child_process */
var utils = require('vertx-js/util/utils');
var StreamInput = require('vertx-exec-js/stream_input');
var Vertx = require('vertx-js/vertx');
var StreamOutput = require('vertx-exec-js/stream_output');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JChildProcess = io.vertx.ext.childprocess.ChildProcess;
var ProcessOptions = io.vertx.ext.childprocess.ProcessOptions;

/**

 @class
*/
var ChildProcess = function(j_val) {

  var j_childProcess = j_val;
  var that = this;

  /**

   @public
   @param handler {function} 
   @return {ChildProcess}
   */
  this.exitHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_childProcess["exitHandler(io.vertx.core.Handler)"](function(jVal) {
      handler(jVal);
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {StreamOutput}
   */
  this.stdin = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (that.cachedstdin == null) {
        that.cachedstdin = utils.convReturnVertxGen(j_childProcess["stdin()"](), StreamOutput);
      }
      return that.cachedstdin;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {StreamInput}
   */
  this.stdout = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (that.cachedstdout == null) {
        that.cachedstdout = utils.convReturnVertxGen(j_childProcess["stdout()"](), StreamInput);
      }
      return that.cachedstdout;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public

   @return {StreamInput}
   */
  this.stderr = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (that.cachedstderr == null) {
        that.cachedstderr = utils.convReturnVertxGen(j_childProcess["stderr()"](), StreamInput);
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
   {@link ChildProcess#isRunning} may return <code>true</code> for a brief period after calling this method.

   @public
   @param force {boolean} if true is passed, the process will be forcibly killed 
   */
  this.destroy = function(force) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] ==='boolean') {
      j_childProcess["destroy(boolean)"](force);
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
      return j_childProcess["isRunning()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_childProcess;
};

/**

 @memberof module:vertx-exec-js/child_process
 @param vertx {Vertx} 
 @param commands {Array.<string>} 
 @param options {Object} 
 @param handler {function} 
 */
ChildProcess.spawn = function() {
  var __args = arguments;
  if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'object' && __args[1] instanceof Array && typeof __args[2] === 'function') {
    JChildProcess["spawn(io.vertx.core.Vertx,java.util.List,io.vertx.core.Handler)"](__args[0]._jdel, utils.convParamListBasicOther(__args[1]), function(jVal) {
    __args[2](utils.convReturnVertxGen(jVal, ChildProcess));
  });
  }else if (__args.length === 4 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'object' && __args[1] instanceof Array && (typeof __args[2] === 'object' && __args[2] != null) && typeof __args[3] === 'function') {
    JChildProcess["spawn(io.vertx.core.Vertx,java.util.List,io.vertx.ext.childprocess.ProcessOptions,io.vertx.core.Handler)"](__args[0]._jdel, utils.convParamListBasicOther(__args[1]), __args[2] != null ? new ProcessOptions(new JsonObject(JSON.stringify(__args[2]))) : null, function(jVal) {
    __args[3](utils.convReturnVertxGen(jVal, ChildProcess));
  });
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = ChildProcess;