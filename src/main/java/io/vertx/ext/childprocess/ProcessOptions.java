package io.vertx.ext.childprocess;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@DataObject
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
    env = json.getJsonObject("env");
    cwd = json.getString("cwd");
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
}
