## Child Process extension for Vert.x

[![childprocess-vertx-ext](https://github.com/vietj/childprocess-vertx-ext/actions/workflows/ci.yml/badge.svg)](https://github.com/vietj/childprocess-vertx-ext/actions/workflows/ci.yml)

Spawn child processes from Vert.x.

Based on https://github.com/brettwooldridge/NuProcess _Low-overhead, non-blocking I/O, external Process implementation for Java_.

## What for ?

[Vert.x 5](http://vertx.io)

## Using Child Process

To use Child Process, add the following dependency to the _dependencies_ section of your build descriptor:

* Maven (in your `pom.xml`):

```xml
<dependency>
  <groupId>io.reactiverse</groupId>
  <artifactId>childprocess-vertx-ext</artifactId>
  <version>2.0.0</version>
</dependency>
```

* Gradle (in your `build.gradle` file):

```groovy
dependencies {
  compile 'io.reactiverse:childprocess-vertx-ext:2.0.0'
}
```

Spawn child processes from Vert.x:

```java
Process
  .create(vertx, "cat")
  .startHandler(process -> {
  process.exitHandler(code -> {
    System.out.println("Process exited: " + code);
  });
  process.stdout().handler(buf -> {
    System.out.println("Process wrote: " + buf);
  });
  StreamOutput stdin = process.stdin();
  stdin.write(Buffer.buffer("hello"));
  stdin.close();
}).start();
```

## Web-site docs

* [Java docs](http://www.julienviet.com/childprocess-vertx-ext/guide/java/index.html)

## Snapshots

Use the dependency

```xml
<dependency>
  <groupId>io.reactiverse</groupId>
  <artifactId>childprocess-vertx-ext</artifactId>
  <version>2.0.0</version>
</dependency>
```

Snapshots are deployed in Sonatype OSS repository: TBD.

## License

Apache License - Version 2.0

## Documentation

The online and published documentation is in `/docs` and is served by GitHub pages with Jekyll.

You can find the actual guide source in [src/main/docs/index.md](src/main/docs/index.md). At compilation time, this
source generates the `jekyll/guide/java/index.md`.

The current documentation is in `/jekyll` and can be preview using Docker and your browser

* generate the documentation
** `mvn compile` to generate `jekyll/guide/java/index.md`
** `mvn site` to generate the javadoc in `jekyll/apidocs`
* run Jekyll
** `cd jekyll`
** `docker-compose up`
* open your browser at http://localhost:4000
