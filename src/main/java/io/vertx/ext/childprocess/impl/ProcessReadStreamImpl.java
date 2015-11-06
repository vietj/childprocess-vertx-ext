package io.vertx.ext.childprocess.impl;

import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.childprocess.ProcessReadStream;

import java.nio.ByteBuffer;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class ProcessReadStreamImpl implements ProcessReadStream {

  private final Context context;
  private Handler<Buffer> handler;
  private Handler<Void> endHandler;
  private ByteBuffer processBuffer;
  private boolean paused;

  public ProcessReadStreamImpl(Context context) {
    this.context = context;
  }

  synchronized void write(ByteBuffer byteBuffer) {
    if (!paused) {
      sendBuffer(byteBuffer);
    } else {
      // It's always the same buffer
      processBuffer = byteBuffer;
    }
  }

  void close() {
    context.runOnContext(v -> {
      if (endHandler != null) {
        endHandler.handle(null);
      }
    });
  }

  private void sendBuffer(ByteBuffer byteBuffer) {
    byte[] bytes = new byte[byteBuffer.remaining()];
    byteBuffer.get(bytes);
    Buffer buffer = Buffer.buffer(bytes);
    int len = buffer.length();
    System.out.println("L=" + len);
    context.runOnContext(v -> {
      if (handler != null) {
        handler.handle(buffer);
      }
    });
  }

  @Override
  public ProcessReadStream exceptionHandler(Handler<Throwable> handler) {
    return this;
  }

  @Override
  public ProcessReadStream handler(Handler<Buffer> handler) {
    this.handler = handler;
    return this;
  }

  @Override
  public synchronized ProcessReadStream pause() {
    paused = true;
    return this;
  }

  @Override
  public synchronized ProcessReadStream resume() {
    if (paused) {
      paused = false;
      if (processBuffer != null) {
        sendBuffer(processBuffer);
        processBuffer = null;
      }
    }
    return this;
  }

  @Override
  public ProcessReadStream endHandler(Handler<Void> handler) {
    this.endHandler = handler;
    return this;
  }
}
