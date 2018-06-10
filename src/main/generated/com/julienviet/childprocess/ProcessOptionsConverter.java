/*
 * Copyright (c) 2014 Red Hat, Inc. and others
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

package com.julienviet.childprocess;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link com.julienviet.childprocess.ProcessOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link com.julienviet.childprocess.ProcessOptions} original class using Vert.x codegen.
 */
public class ProcessOptionsConverter {

  public static void fromJson(JsonObject json, ProcessOptions obj) {
    if (json.getValue("cwd") instanceof String) {
      obj.setCwd((String)json.getValue("cwd"));
    }
    if (json.getValue("env") instanceof JsonObject) {
      java.util.Map<String, java.lang.String> map = new java.util.LinkedHashMap<>();
      json.getJsonObject("env").forEach(entry -> {
        if (entry.getValue() instanceof String)
          map.put(entry.getKey(), (String)entry.getValue());
      });
      obj.setEnv(map);
    }
  }

  public static void toJson(ProcessOptions obj, JsonObject json) {
    if (obj.getCwd() != null) {
      json.put("cwd", obj.getCwd());
    }
    if (obj.getEnv() != null) {
      JsonObject map = new JsonObject();
      obj.getEnv().forEach((key,value) -> map.put(key, value));
      json.put("env", map);
    }
  }
}