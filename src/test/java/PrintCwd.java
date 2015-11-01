import java.io.File;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class PrintCwd {

  public static void main(String[] args) {
    System.out.print(new File("").getAbsolutePath());
  }
}
