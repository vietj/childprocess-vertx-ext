# Chils Process for Vert.x

Spawn child process from Vert.x:

```
Process.exec(vertx, Arrays.asList("cat"), process -> {
  process.stdout().handler(buf -> {
    System.out.println("Process wrote: " + buf);
  });
  ProcessWriteStream stdin = process.stdin();
  stdin.write(Buffer.buffer("hello"));
  stdin.close();
  process.exitHandler(code -> {
    System.out.println("Process exited: " + code);
  });
});
```

Based on https://github.com/brettwooldridge/NuProcess _Low-overhead, non-blocking I/O, external Process implementation for Java_.

## Todo

- documentation
- test destroy
- test isRunning
