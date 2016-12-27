# Child Process for Vert.x

Spawn child process from Vert.x:

```
Process.create(vertx, "cat").spawn(process -> {
  process.stdout().handler(buf -> {
    System.out.println("Process wrote: " + buf);
  });
  StreamOutput stdin = process.stdin();
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
- test isRunning
