/*
 * Copyright (C) 2016 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.reactiverse.childprocess;

import java.io.File;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Main {

  public static void main(String[] args) throws Exception {

    File f = new File(args[0]);
    if (!f.exists()) {
      System.exit(-1);
    }

    while (f.length() == 0) {
      Thread.sleep(1);
    }

    while (true) {
      int c = System.in.read();
      if (c == 'h' || c == 'e' || c == 'l' || c == 'o') {
        int a = 0;
      } else {
        if (c == -1) {
          System.exit(-1);
        } else if (c == 4) {
          break;
        }
      }
    }

  }
}
