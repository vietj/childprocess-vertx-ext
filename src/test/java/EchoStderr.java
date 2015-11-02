/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class EchoStderr {

  public static void main(String[] args) {
    for (String arg : args) {
      System.err.print(arg);
    }
  }
}
