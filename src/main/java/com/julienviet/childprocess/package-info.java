/**
 * == Using Vert.x Child Process
 *
 * To use vert.x child process, add the following dependency to the _dependencies_ section of your build descriptor:
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
 *
 */
@ModuleGen(name = "child-process", groupPackage = "com.julienviet")
@Document(fileName = "index.adoc")
package com.julienviet.childprocess;

import io.vertx.codegen.annotations.ModuleGen;
import io.vertx.docgen.Document;
