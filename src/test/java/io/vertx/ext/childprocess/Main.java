package io.vertx.ext.childprocess;

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
