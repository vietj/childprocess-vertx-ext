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

/** @module child-process-js/stream_input */
var utils = require('vertx-js/util/utils');
var Buffer = require('vertx-js/buffer');
var StreamBase = require('vertx-js/stream_base');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JStreamInput = com.julienviet.childprocess.StreamInput;

/**
 The input of a process.

 @class
*/
var StreamInput = function(j_val) {

  var j_streamInput = j_val;
  var that = this;
  StreamBase.call(this, j_val);

  /**
   Set an exception handler on the read stream.

   @public
   @param handler {function} the exception handler 
   @return {StreamInput} a reference to this, so the API can be used fluently
   */
  this.exceptionHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_streamInput["exceptionHandler(io.vertx.core.Handler)"](function(jVal) {
      handler(utils.convReturnThrowable(jVal));
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Set a buffer handler. As bytes are read, the handler will be called with the data.

   @public
   @param handler {function} 
   @return {StreamInput} a reference to this, so the API can be used fluently
   */
  this.handler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_streamInput["handler(io.vertx.core.Handler)"](function(jVal) {
      handler(utils.convReturnVertxGen(jVal, Buffer));
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Set an end handler. Once the stream has ended, and there is no more data to be read, this handler will be called.

   @public
   @param handler {function} 
   @return {StreamInput} a reference to this, so the API can be used fluently
   */
  this.endHandler = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_streamInput["endHandler(io.vertx.core.Handler)"](handler);
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_streamInput;
};

// We export the Constructor function
module.exports = StreamInput;