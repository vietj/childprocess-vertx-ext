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

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Options for spawning new processes.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@DataObject(generateConverter = true)
public class ProcessOptions {

  /**
   * The default environment variables = the current process environment variables
   */
  public static final Map<String, String> DEFAULT_ENV = System.getenv();

  private Map<String, String> env;
  private String cwd;

  public ProcessOptions() {
    this.env = new HashMap<>(DEFAULT_ENV);
  }

  public ProcessOptions(ProcessOptions that) {
    env = that.env != null ? new HashMap<>(that.env) : null;
    cwd = that.cwd;
  }

  public ProcessOptions(JsonObject json) {
    ProcessOptionsConverter.fromJson(json, this);
  }

  /**
   * @return the current options environment variables as key-value pairs
   */
  public Map<String, String> getEnv() {
    return env;
  }

  /**
   * Set the child process environment variables as key-value pairs.
   *
   * @param env the env variables
   * @return a reference to this, so the API can be used fluently
   */
  public ProcessOptions setEnv(Map<String, String> env) {
    this.env = env;
    return this;
  }

  /**
   * @return the child process current working directory
   */
  public String getCwd() {
    return cwd;
  }

  /**
   * Set the child process current working directory.
   *
   * @param cwd the current working directory
   * @return a reference to this, so the API can be used fluently
   */
  public ProcessOptions setCwd(String cwd) {
    this.cwd = cwd;
    return this;
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    ProcessOptionsConverter.toJson(this, json);
    return json;
  }
}
