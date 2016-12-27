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

package io.vertx.ext.childprocess;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link io.vertx.ext.childprocess.ProcessOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link io.vertx.ext.childprocess.ProcessOptions} original class using Vert.x codegen.
 */
public class ProcessOptionsConverter {

  public static void fromJson(JsonObject json, ProcessOptions obj) {
    if (json.getValue("cwd") instanceof String) {
      obj.setCwd((String)json.getValue("cwd"));
    }
    if (json.getValue("env") instanceof JsonObject) {
      obj.setEnv(((JsonObject)json.getValue("env")).copy());
    }
  }

  public static void toJson(ProcessOptions obj, JsonObject json) {
    if (obj.getCwd() != null) {
      json.put("cwd", obj.getCwd());
    }
    if (obj.getEnv() != null) {
      json.put("env", obj.getEnv());
    }
  }
}