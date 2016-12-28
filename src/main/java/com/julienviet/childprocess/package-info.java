/**
 * = Child Process for Vert.x
 * :toc: left
 *
 * Child Process is a Vert.x component for spawning OS child processes.
 *
 * * based on https://github.com/brettwooldridge/NuProcess _Low-overhead, non-blocking I/O, external Process implementation for Java_.
 * * standard streams are fully non blocking
 * * spawned process can be killed
 *
 * == Using Child Process
 *
 * To use Child Process, add the following dependency to the _dependencies_ section of your build descriptor:
 *
 * * Maven (in your `pom.xml`):
 *
 * [source,xml,subs="+attributes"]
 * ----
 * <dependency>
 *   <groupId>${maven.groupId}</groupId>
 *   <artifactId>${maven.artifactId}</artifactId>
 *   <version>${maven.version}</version>
 * </dependency>
 * ----
 *
 * * Gradle (in your `build.gradle` file):
 *
 * [source,groovy,subs="+attributes"]
 * ----
 * dependencies {
 *   compile '${maven.groupId}:${maven.artifactId}:${maven.version}'
 * }
 * ----
 *
 * == Spawning child processes
 *
 * You can spawn child processes with the {@link com.julienviet.childprocess.Process#spawn} method:
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex01}
 * ----
 *
 * you can give arguments to child processes
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex02}
 * ----
 *
 * by default child processes use the current process environment options, you can pass key-value pairs
 * as new environment variables
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex03}
 * ----
 *
 * {@link com.julienviet.childprocess.Process#env()} gives you the current process environment key-value pairs
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex04}
 * ----
 *
 * By default, the child processes uses the current process _current working directory_, the
 * {@link com.julienviet.childprocess.ProcessOptions#setCwd(java.lang.String)} option overrides it
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex05}
 * ----
 *
 * == Interacting with child processes
 *
 * The child process streams are available as
 *
 * * {@link com.julienviet.childprocess.Process#stdin()}
 * * {@link com.julienviet.childprocess.Process#stdout()}
 * * {@link com.julienviet.childprocess.Process#stderr()}
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex10}
 * ----
 *
 * Calling {@link com.julienviet.childprocess.Process#kill()} kills the child process, on POSIX it sends the
 * `SIGTERM` signal.
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex30}
 * ----
 *
 * Child processes can also be forcibly killed
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex31}
 * ----
 *
 * == Child process lifecycle
 *
 * You can be aware of the child process termination
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex20}
 * ----
 *
 * == Delayed start
 *
 * Calling {@link com.julienviet.childprocess.Process#spawn} starts the process after the current event loop task
 * execution, so you can set handlers on the process without a race condition.
 *
 * Sometimes you want to delay the start of the child process you've created, for instance you are creating a process
 * from a non Vert.x thread:
 *
 * [source,$lang]
 * ----
 * {@link examples.Examples#ex40}
 * ----
 */
@ModuleGen(name = "child-process", groupPackage = "com.julienviet")
@Document(fileName = "index.adoc")
package com.julienviet.childprocess;

import io.vertx.codegen.annotations.ModuleGen;
import io.vertx.docgen.Document;
