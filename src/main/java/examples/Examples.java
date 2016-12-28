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

package examples;

import com.julienviet.childprocess.Process;
import com.julienviet.childprocess.ProcessOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.docgen.Source;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
@Source
public class Examples {

  public void ex01(Vertx vertx) {
    Process.spawn(vertx, "ls");
  }

  public void ex02(Vertx vertx) {
    Process.spawn(vertx, "ls", Arrays.asList("-lh", "/usr"));
  }

  public void ex03(Vertx vertx) {
    Map<String, String> env = new HashMap<>();
    env.put("MY_VAR", "whatever");
    Process.spawn(vertx, "ls", new ProcessOptions().setEnv(env));
  }

  public void ex04(Vertx vertx) {
    ProcessOptions options = new ProcessOptions().setEnv(Process.env());
    Process.spawn(vertx, "ls", options);
  }

  public void ex05(Vertx vertx) {
    ProcessOptions options = new ProcessOptions().setCwd("/some-dir");
    Process.spawn(vertx, "ls", options);
  }

  public void ex10(Vertx vertx) {
    Process process = Process.spawn(vertx, "cat");

    process.stdout().handler(buff -> {
      System.out.println(buff.toString());
    });

    process.stdin().write(Buffer.buffer("Hello World"));
  }

  public void ex20(Vertx vertx) {
    Process process = Process.spawn(vertx, "sleep", Arrays.asList("2"));

    process.exitHandler(code -> {
      System.out.println("Child process exited with code: " + code);
    });
  }

  public void ex30(Vertx vertx) {
    Process process = Process.spawn(vertx, "cat");

    process.stdout().handler(buff -> {
      System.out.println(buff.toString());
    });

    process.stdin().write(Buffer.buffer("Hello World"));

    // Kill the process
    process.kill();
  }

  public void ex31(Vertx vertx) {
    Process process = Process.spawn(vertx, "cat");

    process.stdout().handler(buff -> {
      System.out.println(buff.toString());
    });

    process.stdin().write(Buffer.buffer("Hello World"));

    // Kill the process forcibly
    process.kill(true);
  }

  public void ex40(Vertx vertx) {
    Process process = Process.create(vertx, "echo \"Hello World\"");

    process.stdout().handler(buff -> {
      System.out.println(buff.toString());
    });

    // Start the process
    process.start();
  }
}
