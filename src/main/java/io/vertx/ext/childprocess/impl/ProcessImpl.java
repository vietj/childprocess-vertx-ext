package io.vertx.ext.childprocess.impl;

import com.zaxxer.nuprocess.NuProcess;
import com.zaxxer.nuprocess.NuProcessHandler;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.childprocess.*;
import io.vertx.ext.childprocess.Process;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class ProcessImpl implements NuProcessHandler, Process, StreamOutput {

  private static final int OPEN = 0, CLOSING = 1, CLOSED = 2;

  private int stdinStatus = OPEN;
  private final ArrayDeque<Buffer> stdinPending = new ArrayDeque<>();
  private int stdinSize;
  private int stdinMaxSize = 1024;
  private Handler<Void> drainHandler;
  private final Context context;
  private final ProcessStreamInput stdout;
  private final ProcessStreamInput stderr;
  private final Handler<Process> handler;
  private Handler<Integer> exitHandler;
  private NuProcess process;
  private boolean wantWrite;

  public ProcessImpl(Context context, Handler<Process> handler) {
    this.context = context;
    this.stdout = new ProcessStreamInput(context);
    this.stderr = new ProcessStreamInput(context);
    this.handler = handler;
  }

  //

  @Override
  public synchronized Process exitHandler(Handler<Integer> handler) {
    exitHandler = handler;
    return this;
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
  public StreamOutput write(Buffer buffer) {
    boolean hasPending;
    synchronized (this) {
      if (stdinStatus != OPEN) {
        throw new IllegalStateException();
      }
      stdinPending.add(buffer);
      stdinSize += buffer.length();
      hasPending = stdinSize > 0;
    }
    if (process != null && hasPending && !wantWrite) {
      wantWrite = true;
      process.wantWrite();
    }
    return this;
  }

  @Override
  public synchronized StreamOutput setWriteQueueMaxSize(int i) {
    stdinMaxSize = i;
    return this;
  }

  @Override
  public StreamOutput drainHandler(Handler<Void> handler) {
    drainHandler = handler;
    checkDrained();
    return this;
  }

  @Override
  public synchronized boolean writeQueueFull() {
    return stdinSize > stdinMaxSize;
  }

  @Override
  public void close() {
    synchronized (this) {
      switch (stdinStatus) {
        case OPEN:
          if (process != null) {
            if (stdinSize == 0) {
              stdinStatus = CLOSED;
            } else {
              stdinStatus = CLOSING;
              return;
            }
          } else {
            // We close the stream before the process started
            stdinStatus = CLOSING;
            return;
          }
          break;
        default:
          return;
      }
    }
    process.closeStdin(false);
  }

  //

  @Override
  public void onPreStart(NuProcess nuProcess) {
  }

  @Override
  public void onStart(NuProcess nuProcess) {
    context.runOnContext(v -> {
      process = nuProcess;
      handler.handle(this);
    });
  }

  @Override
  public synchronized void onExit(int exitCode) {
    if (exitHandler != null) {
      context.runOnContext(v -> {
        process = null;
        synchronized (this) {
          stdinStatus = CLOSED;
        }
        exitHandler.handle(exitCode);
      });
    }
  }

  @Override
  public void onStdout(ByteBuffer byteBuffer, boolean closed) {
    if (byteBuffer != null && byteBuffer.remaining() > 0) {
      stdout.write(byteBuffer);
    }
    if (closed) {
      stdout.close();
    }
  }

  @Override
  public void onStderr(ByteBuffer byteBuffer, boolean closed) {
    if (byteBuffer != null && byteBuffer.remaining() > 0) {
      stderr.write(byteBuffer);
    }
    if (closed) {
      stderr.close();
    }
  }

  @Override
  public synchronized boolean onStdinReady(ByteBuffer byteBuffer) {
    Buffer buffer;
    while (byteBuffer.remaining() > 0 && (buffer = stdinPending.poll()) != null) {
      byte[] bytes;
      if (buffer.length() <= byteBuffer.remaining()) {
        bytes = buffer.getBytes();
      } else {
        bytes = buffer.getBytes(0, byteBuffer.remaining());
        stdinPending.addFirst(buffer.slice(byteBuffer.remaining(), buffer.length()));
      }
      byteBuffer.put(bytes); // See to do directly with Netty ByteBuf
      stdinSize -= bytes.length;
    }
    byteBuffer.flip();
    context.runOnContext(v -> checkDrained());
    if (stdinSize > 0) {
      return true;
    } else {
      wantWrite = false;
      if (stdinStatus == CLOSING) {
        stdinStatus = CLOSED;
        process.closeStdin(false);
      }
      return false;
    }
  }

  private void checkDrained() {
    synchronized (this) {
      if (stdinSize >= stdinMaxSize / 2) {
        return;
      }
    }
    if (drainHandler != null) {
      Handler<Void> handler = drainHandler;
      drainHandler = null;
      handler.handle(null);
    }
  }

  @Override
  public void destroy(boolean force) {
    if (process != null) {
      process.destroy(force);
    }
  }

  @Override
  public boolean isRunning() {
    return process != null && process.isRunning();
  }
}
