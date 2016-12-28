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
