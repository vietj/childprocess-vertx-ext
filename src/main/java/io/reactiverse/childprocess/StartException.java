package io.reactiverse.childprocess;

import io.vertx.core.buffer.Buffer;

public class StartException extends Exception {

  final int exitCode;
  final Buffer stdout;
  final Buffer stdin;

  public StartException(int exitCode, Buffer stdout, Buffer stdin) {
    this.exitCode = exitCode;
    this.stdout = stdout;
    this.stdin = stdin;
  }
}
