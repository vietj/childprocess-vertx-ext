/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class PrintEnv {

  public static void main(String[] args) {
    String val = System.getenv(args[0]);
    if (val != null) {
      System.out.print(val);
    }
  }
}
