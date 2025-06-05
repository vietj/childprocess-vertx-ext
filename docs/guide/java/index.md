# Child Process extension for Vert.x

Child Process is a Vert.x component for spawning OS child processes.

* based on https://github.com/brettwooldridge/NuProcess _Low-overhead, non-blocking I/O, external Process implementation for Java_.
* standard streams are fully non-blocking
* spawned process management

## Using Child Process

To use Child Process, add the following dependency to the _dependencies_ section of your build descriptor:

* Maven (in your `pom.xml`):

```xml
<dependency>
  <groupId>io.reactiverse</groupId>
  <artifactId>childprocess-vertx-ext</artifactId>
  <version>2.0.2-SNAPSHOT</version>
</dependency>
```

* Gradle (in your `build.gradle` file):

```java
dependencies {
  compile 'io.reactiverse:childprocess-vertx-ext:2.0.2-SNAPSHOT'
}
```

## Spawning child processes

You can spawn child processes with the [`Process.create`](../../apidocs/io/reactiverse/childprocess/Process.html#create-io.vertx.core.Vertx-java.lang.String-) and  [`start`](../../apidocs/io/reactiverse/childprocess/ProcessBuilder.html#start--) methods:

```java
ProcessBuilder processBuilder = Process.create(vertx, "ls");

// Start the process
Future<Void> fut = processBuilder.start();
```

the future returned by `start` completes when the process has started or failed

you can give arguments to child processes

```java
Process.create(vertx, "ls", Arrays.asList("-lh", "/usr")).start();
```

by default child processes use the current process environment options, you can pass key-value pairs as new environment variables

```java
Map<String, String> env = new HashMap<>();
env.put("MY_VAR", "whatever");
Process.create(vertx, "ls", new ProcessOptions().setEnv(env)).start();
```

[`Process.env`](../../apidocs/io/reactiverse/childprocess/Process.html#env--) gives you the current process environment key-value pairs

```java
ProcessOptions options = new ProcessOptions().setEnv(Process.env());
Process.create(vertx, "ls", options).start();
```

By default, the child processes uses the current process _current working directory_, the
[`setCwd`](../../apidocs/io/reactiverse/childprocess/ProcessOptions.html#setCwd-java.lang.String-) option overrides it

```java
ProcessOptions options = new ProcessOptions().setCwd("/some-dir");
Process.create(vertx, "ls", options).start();
```

## Interacting with child processes

The child process streams are available as

* [`stdin`](../../apidocs/io/reactiverse/childprocess/Process.html#stdin--)
* [`stdout`](../../apidocs/io/reactiverse/childprocess/Process.html#stdout--)
* [`stderr`](../../apidocs/io/reactiverse/childprocess/Process.html#stderr--)

```java
ProcessBuilder processBuilder = Process.create(vertx, "cat");

processBuilder.startHandler(process -> {
  process.stdout().handler(buff -> {
    System.out.println(buff.toString());
  });

  process.stdin().write(Buffer.buffer("Hello World"));
});

processBuilder.start();
```

Calling [`kill`](../../apidocs/io/reactiverse/childprocess/Process.html#kill--) kills the child process, on POSIX it sends the
`SIGTERM` signal.

```java
Process
  .create(vertx, "cat")
  .startHandler(process -> {

    process.stdout().handler(buff -> {
      System.out.println(buff.toString());
    });

    process.stdin().write(Buffer.buffer("Hello World"));

    // Kill the process
    process.kill();
  })
  .start();
```

Child processes can also be forcibly killed

```java
Process
  .create(vertx, "cat")
  .startHandler(process -> {

    process.stdout().handler(buff -> {
      System.out.println(buff.toString());
    });

    process.stdin().write(Buffer.buffer("Hello World"));

    // Kill the process forcibly
    process.kill(true);

}).start();
```

## Child process lifecycle

You can be aware of the child process termination

```java
Process
  .create(vertx, "sleep", Arrays.asList("2"))
  .startHandler(process -> {
    process.exitHandler(code -> {
      System.out.println("Child process exited with code: " + code);
    });
  })
  .start();
```