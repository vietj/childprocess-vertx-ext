/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class Shutdown {

  public static void main(String[] args) throws Exception {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.out.print("exited");
      }
    });
    System.out.print("ok");
    Thread.sleep(10000);
  }
}
