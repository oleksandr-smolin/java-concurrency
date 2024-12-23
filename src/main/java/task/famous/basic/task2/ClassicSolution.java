package task.famous.basic.task2;

public class ClassicSolution {

  private static boolean allowedThreadFlag = false;

  public static void main(String[] args) throws InterruptedException {

    startPingPongGame();

    Thread.sleep(2000);
  }

  private static void startPingPongGame() {

    var threadPing = new Thread(() -> {
      while(true) printSound("Ping", true);
    });
    threadPing.setDaemon(true);

    var threadPong = new Thread(() -> {
      while (true) printSound("Pong", false);
    });
    threadPong.setDaemon(true);

    threadPing.start();
    threadPong.start();
  }

  private synchronized static void printSound(String sound, boolean threadFlag) {
    while (allowedThreadFlag != threadFlag) {
      try {
        ClassicSolution.class.wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    System.out.println(sound);
    allowedThreadFlag = !allowedThreadFlag;
    ClassicSolution.class.notify();
  }
}
