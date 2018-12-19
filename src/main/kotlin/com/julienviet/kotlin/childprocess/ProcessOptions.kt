package com.julienviet.kotlin.childprocess

import com.julienviet.childprocess.ProcessOptions

/**
 * A function providing a DSL for building [com.julienviet.childprocess.ProcessOptions] objects.
 *
 * Options for spawning new processes.
 *
 * @param cwd  Set the child process current working directory.
 * @param env  Set the child process environment variables as key-value pairs.
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [com.julienviet.childprocess.ProcessOptions original] using Vert.x codegen.
 */
fun ProcessOptions(
  cwd: String? = null,
  env: Map<String, String>? = null): ProcessOptions = com.julienviet.childprocess.ProcessOptions().apply {

  if (cwd != null) {
    this.setCwd(cwd)
  }
  if (env != null) {
    this.setEnv(env)
  }
}

