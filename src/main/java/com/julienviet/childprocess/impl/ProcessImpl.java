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

package com.julienviet.childprocess.impl;

import com.julienviet.childprocess.StreamInput;
import com.julienviet.childprocess.StreamOutput;
import com.zaxxer.nuprocess.NuProcess;
import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import com.julienviet.childprocess.Process;
import io.vertx.core.internal.ContextInternal;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class ProcessImpl implements Process, StreamOutput {

  private static final int OPEN = 0, CLOSING = 1, CLOSED = 2;

  private int stdinStatus = OPEN;
  private Promise<Void> stdninEnd;
  private final ArrayDeque<Write> stdinPending = new ArrayDeque<>();
  private int stdinPendingSize;
  private int stdinMaxSize = 1024;
  private Handler<Void> drainHandler;
  private final ContextInternal context;
  private final ProcessStreamInput stdout;
  private final ProcessStreamInput stderr;
  private Handler<Integer> exitHandler;
  private final NuProcess process;
  private Promise<Integer> exitFuture;
  private boolean wantWrite;

  public ProcessImpl(ContextInternal context, NuProcess process) {
    this.context = context;
    this.process = process;
    this.stdout = new ProcessStreamInput(context);
    this.stderr = new ProcessStreamInput(context);
    this.exitFuture = context.promise();
    this.stdninEnd = context.promise();
  }

  //

  @Override
  public synchronized Process exitHandler(Handler<Integer> handler) {
    exitHandler = handler;
    return this;
  }

  @Override
  public synchronized Integer pid() {
    return process.getPID();
  }

  @Override
  public StreamOutput stdin() {
    return this;
  }

  @Override
  public StreamInput stdout() {
    return stdout;
  }

  @Override
  public StreamInput stderr() {
    return stderr;
  }

  //

  @Override
  public StreamOutput exceptionHandler(Handler<Throwable> handler) {
    return this;
  }

  @Override
  public Future<Void> write(Buffer buffer) {
    Promise<Void> promise = context.promise();
    synchronized (this) {
      if (stdinStatus == CLOSING || stdinStatus == CLOSED) {
        throw new IllegalStateException();
      }
      stdinPending.add(new Write(buffer, promise));
      stdinPendingSize += buffer.length();
      if (process != null && !wantWrite) {
        wantWrite = true;
        process.wantWrite();
      }
    }
    return promise.future();
  }

  @Override
  public synchronized StreamOutput setWriteQueueMaxSize(int i) {
    stdinMaxSize = i;
    return this;
  }

  @Override
  public StreamOutput drainHandler(Handler<Void> handler) {
    synchronized (this) {
      drainHandler = handler;
    }
    checkDrained();
    return this;
  }

  @Override
  public synchronized boolean writeQueueFull() {
    return stdinPendingSize > stdinMaxSize;
  }

  @Override
  public Future<Void> close() {
    synchronized (this) {
      if (stdinStatus != OPEN) {
        return stdninEnd.future();
      }
      if (stdinPendingSize == 0) {
        stdinStatus = CLOSED;
      } else {
        stdinStatus = CLOSING;
        return stdninEnd.future();
      }
    }
    process.closeStdin(false);
    stdninEnd.complete();
    return stdninEnd.future();
  }

  //

  public synchronized void onExit(int exitCode) {
    synchronized (this) {
      stdinStatus = CLOSED;
    }
    handleExit(exitCode);
  }

  private void handleExit(int exitCode) {
    exitFuture.complete(exitCode);
    Handler<Integer> handler = exitHandler;
    if (handler != null) {
      context.emit(exitCode, handler);
    }
  }

  public void onStdout(Buffer buffer, boolean closed) {
    if (buffer != null) {
      stdout.write(buffer);
    }
    if (closed) {
      stdout.close();
    }
  }

  public void onStderr(Buffer buffer, boolean closed) {
    if (buffer != null) {
      stderr.write(buffer);
    }
    if (closed) {
      stderr.close();
    }
  }

  public boolean onStdinReady(ByteBuffer byteBuffer) {
    synchronized (this) {
      Write write;
      while (byteBuffer.remaining() > 0 && (write = stdinPending.poll()) != null) {
        byte[] bytes;
        if (write.buffer.length() <= byteBuffer.remaining()) {
          bytes = write.buffer.getBytes();
          write.promise.complete();
        } else {
          bytes = write.buffer.getBytes(0, byteBuffer.remaining());
          stdinPending.addFirst(new Write(write.buffer.slice(byteBuffer.remaining(), write.buffer.length()), write.promise));
        }
        byteBuffer.put(bytes); // See to do directly with Netty ByteBuf
        stdinPendingSize -= bytes.length;
      }
      byteBuffer.flip();
      context.execute(v -> checkDrained());
      if (stdinPendingSize > 0) {
        return true;
      } else {
        wantWrite = false;
        if (stdinStatus == CLOSING) {
          stdinStatus = CLOSED;
        } else {
          return false;
        }
      }
    }
    process.closeStdin(false);
    stdninEnd.complete();
    return false;
  }

  private void checkDrained() {
    Handler<Void> handler;
    synchronized (this) {
      if (stdinPendingSize >= stdinMaxSize / 2) {
        return;
      }
      handler = drainHandler;
      drainHandler = null;
    }
    if (handler != null) {
      context.emit(handler);
    }
  }

  @Override
  public void kill(boolean force) {
    if (process != null) {
      process.destroy(force);
    }
  }

  @Override
  public boolean isRunning() {
    return process.isRunning();
  }

  private static class Write {
    final Buffer buffer;
    final Promise<Void> promise;
    Write(Buffer buffer, Promise<Void> promise) {
      this.buffer = buffer;
      this.promise = promise;
    }
  }
}
