/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class StdoutLongSequence {

  public static void main(String[] args) {
    for (int i = 0;i < 100000;i++) {
      System.out.print("" + i);
    }
  }
}
