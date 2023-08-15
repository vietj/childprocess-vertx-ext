package com.julienviet.childprocess.impl;

import com.julienviet.childprocess.Process;
import com.julienviet.childprocess.ProcessBuilder;
import com.julienviet.childprocess.ProcessOptions;
import com.julienviet.childprocess.StartException;
import com.zaxxer.nuprocess.NuProcess;
import com.zaxxer.nuprocess.NuProcessBuilder;
import com.zaxxer.nuprocess.NuProcessHandler;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.ContextInternal;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessBuilderImpl implements ProcessBuilder {

  private final ContextInternal context;
  private final String command;
  private final List<String> args;
  private final ProcessOptions options;
  private Handler<Process> processHandler;

  public ProcessBuilderImpl(ContextInternal context, String command, List<String> args, ProcessOptions options) {
    this.context = context;
    this.command = command;
    this.args = args;
    this.options = options;
  }

  @Override
  public Future<Void> start() {
    Promise<Void> promise = context.promise();
    Map<String, String> env = new HashMap<>();
    if (options.getEnv() != null) {
      options.getEnv().entrySet().forEach(entry -> {
        if (entry.getValue() != null) {
          env.put(entry.getKey(), entry.getValue());
        }
      });
    }
    ArrayList<String> commands = new ArrayList<>();
    commands.add(command);
    commands.addAll(args);
    NuProcessBuilder builder = new NuProcessBuilder(commands, env);
    if (options.getCwd() != null) {
      builder.setCwd(new File(options.getCwd()).toPath());
    }
    Buffer bufferedStdout = Buffer.buffer();
    Buffer bufferedStderr = Buffer.buffer();
    Handler<Process> handler = processHandler; // Capture
    builder.setProcessListener(new NuProcessHandler() {
      volatile ProcessImpl process;
      @Override
      public void onPreStart(NuProcess nuProcess) {
      }
      @Override
      public void onStart(NuProcess nuProcess) {
        ProcessImpl p = new ProcessImpl(context, nuProcess);
        process = p;
        if (handler != null) {
          context.emit(p, handler);
        }
        promise.complete();
      }
      @Override
      public void onExit(int exitCode) {
        ProcessImpl p = process;
        if (p == null) {
          promise.fail(new StartException(exitCode, bufferedStdout, bufferedStderr));
        } else {
          p.onExit(exitCode);
        }
      }
      private byte[] getBytes(ByteBuffer byteBuffer) {
        if (byteBuffer != null && byteBuffer.remaining() > 0) {
          byte[] bytes = new byte[byteBuffer.remaining()];
          byteBuffer.get(bytes);
          return bytes;
        } else {
          return null;
        }
      }
      @Override
      public void onStdout(ByteBuffer byteBuffer, boolean closed) {
        byte[] bytes = getBytes(byteBuffer);
        ProcessImpl p = process;
        if (process != null) {
          p.onStdout(bytes != null ? Buffer.buffer(bytes) : null, closed);
        } else {
          if (bytes != null) {
            bufferedStdout.appendBytes(bytes);
          }
        }
      }
      @Override
      public void onStderr(ByteBuffer byteBuffer, boolean closed) {
        byte[] bytes = getBytes(byteBuffer);
        ProcessImpl p = process;
        if (p != null) {
          p.onStderr(bytes != null ? Buffer.buffer(bytes) : null, closed);
        } else {
          if (bytes != null) {
            bufferedStdout.appendBytes(bytes);
          }
        }
      }
      @Override
      public boolean onStdinReady(ByteBuffer buffer) {
        ProcessImpl p = process;
        return p.onStdinReady(buffer);
      }
    });
    builder.start();
    return promise.future();
  }

  @Override
  public synchronized ProcessBuilder startHandler(Handler<Process> handler) {
    processHandler = handler;
    return this;
  }
}
