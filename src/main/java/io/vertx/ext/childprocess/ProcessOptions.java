package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@DataObject(generateConverter = true)
public class ProcessOptions {

  private JsonObject env;
  private String cwd;

  public ProcessOptions() {
  }

  public ProcessOptions(ProcessOptions that) {
    env = that.env != null ? that.env.copy() : null;
    cwd = that.cwd;
  }

  public ProcessOptions(JsonObject json) {
    ProcessOptionsConverter.fromJson(json, this);
  }

  public JsonObject getEnv() {
    return env;
  }

  public ProcessOptions setEnv(JsonObject env) {
    this.env = env;
    return this;
  }

  public String getCwd() {
    return cwd;
  }

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
