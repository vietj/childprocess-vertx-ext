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
  <groupId>${maven.groupId}</groupId>
  <artifactId>${maven.artifactId}</artifactId>
  <version>${maven.version}</version>
</dependency>
```

* Gradle (in your `build.gradle` file):

```java
dependencies {
  compile '${maven.groupId}:${maven.artifactId}:${maven.version}'
}
```

## Spawning child processes

You can spawn child processes with the {@link io.reactiverse.childprocess.Process#create} and  {@link io.reactiverse.childprocess.ProcessBuilder#start} methods:

```java
{@link examples.Examples#ex01}
```

the future returned by `start` completes when the process has started or failed

you can give arguments to child processes

```java
{@link examples.Examples#ex02}
```

by default child processes use the current process environment options, you can pass key-value pairs as new environment variables

```java
{@link examples.Examples#ex03}
```

{@link io.reactiverse.childprocess.Process#env()} gives you the current process environment key-value pairs

```java
{@link examples.Examples#ex04}
```

By default, the child processes uses the current process _current working directory_, the
{@link io.reactiverse.childprocess.ProcessOptions#setCwd(java.lang.String)} option overrides it

```java
{@link examples.Examples#ex05}
```

## Interacting with child processes

The child process streams are available as

* {@link io.reactiverse.childprocess.Process#stdin()}
* {@link io.reactiverse.childprocess.Process#stdout()}
* {@link io.reactiverse.childprocess.Process#stderr()}

```java
{@link examples.Examples#ex10}
```

Calling {@link io.reactiverse.childprocess.Process#kill()} kills the child process, on POSIX it sends the
`SIGTERM` signal.

```java
{@link examples.Examples#ex30}
```

Child processes can also be forcibly killed

```java
{@link examples.Examples#ex31}
```

## Child process lifecycle

You can be aware of the child process termination

```java
{@link examples.Examples#ex20}
```
