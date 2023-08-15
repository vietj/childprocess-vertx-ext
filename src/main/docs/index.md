# Child Process extension for Vert.x

Child Process is a Vert.x component for spawning OS child processes.

* based on https://github.com/brettwooldridge/NuProcess _Low-overhead, non-blocking I/O, external Process implementation for Java_.
* standard streams are fully non blocking
* spawned process can be killed

## Using Child Process

To use Child Process, add the following dependency to the _dependencies_ section of your build descriptor:

* Maven (in your `pom.xml`):

```xml
<dependency>
  <groupId>${maven.groupId}</groupId>
  <artifactId>${maven.artifactId}</artifactId>
  <version>${maven.version}</version>
</dependency>
```

* Gradle (in your `build.gradle` file):

```$lang
dependencies {
  compile '${maven.groupId}:${maven.artifactId}:${maven.version}'
}
```

## Spawning child processes

You can spawn child processes with the {@link com.julienviet.childprocess.Process#create} and  {@link com.julienviet.childprocess.ProcessBuilder#start} methods:

```$lang
{@link examples.Examples#ex01}
```

the future returned by `start` completes when the process has started or failed

you can give arguments to child processes

```$lang
{@link examples.Examples#ex02}
```

by default child processes use the current process environment options, you can pass key-value pairs as new environment variables

```$lang
{@link examples.Examples#ex03}
```

{@link com.julienviet.childprocess.Process#env()} gives you the current process environment key-value pairs

```$lang
{@link examples.Examples#ex04}
```

By default, the child processes uses the current process _current working directory_, the
{@link com.julienviet.childprocess.ProcessOptions#setCwd(java.lang.String)} option overrides it

```$lang
{@link examples.Examples#ex05}
```

## Interacting with child processes

The child process streams are available as

* {@link com.julienviet.childprocess.Process#stdin()}
* {@link com.julienviet.childprocess.Process#stdout()}
* {@link com.julienviet.childprocess.Process#stderr()}

```$lang
{@link examples.Examples#ex10}
```

Calling {@link com.julienviet.childprocess.Process#kill()} kills the child process, on POSIX it sends the
`SIGTERM` signal.

```$lang
{@link examples.Examples#ex30}
```

Child processes can also be forcibly killed

```$lang
{@link examples.Examples#ex31}
```

## Child process lifecycle

You can be aware of the child process termination

```$lang
{@link examples.Examples#ex20}
```
