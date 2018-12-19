## Child Process extension for Vert.x

Spawn child processes from Vert.x.

Based on https://github.com/brettwooldridge/NuProcess _Low-overhead, non-blocking I/O, external Process implementation for Java_.

## What for ?

[Vert.x 3.6.2](http://vertx.io)

## Using Child Process

To use Child Process, add the following dependency to the _dependencies_ section of your build descriptor:

* Maven (in your `pom.xml`):

```
<dependency>
  <groupId>com.julienviet</groupId>
  <artifactId>childprocess-vertx-ext</artifactId>
  <version>1.3.0</version>
</dependency>
```

* Gradle (in your `build.gradle` file):

```
dependencies {
  compile 'com.julienviet:childprocess-vertx-ext:1.3.0'
}
```

Spawn child processes from Vert.x:

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

## Web-site docs

* [Java docs](http://www.julienviet.com/childprocess-vertx-ext/guide/java/index.html)
* [Groovy docs](http://www.julienviet.com/childprocess-vertx-ext/guide/groovy/index.html)
* [Ruby docs](http://www.julienviet.com/childprocess-vertx-ext/guide/ruby/index.html)
* [JavaScript docs](http://www.julienviet.com/childprocess-vertx-ext/guide/js/index.html)

## Previous Vert.x versions

- Vert.x `3.5.x` : use `1.2.2`
- Vert.x `3.4.x` : use `1.1.2`

## Snapshots

[![Build Status](https://travis-ci.org/vietj/childprocess-vertx-ext.svg?branch=master)](https://travis-ci.org/vietj/childprocess-vertx-ext)

Use the dependency

```
<dependency>
  <groupId>com.julienviet</groupId>
  <artifactId>childprocess-vertx-ext</artifactId>
  <version>1.3.1-SNAPSHOT</version>
</dependency>
```

Snapshots are deploy in Sonatype OSS repository: https://oss.sonatype.org/content/repositories/snapshots/com/julienviet/childprocess-vertx-ext/

## License

Apache License - Version 2.0

## Publishing docs

* mvn package -Pdocs
* cp -r target/docs docs/
* mv docs/childprocess-vertx-ext docs/guide
