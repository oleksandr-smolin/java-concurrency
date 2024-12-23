package course.pyatakha;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

public class Task2 {

  public static void main(String[] args) throws InterruptedException {

    final int[] intervals = {1000, 1500, 1200, 1800, 2000, 1700, 1100, 1600, 1300, 1900};
    final String[] messages = {
        "Starting task...",
        "Processing data...",
        "Connecting to server...",
        "Downloading files...",
        "Uploading results...",
        "Running diagnostics...",
        "Saving progress...",
        "Generating report...",
        "Sending notifications...",
        "Task completed!"
    };

    Spam spam = new Spam(intervals, messages);
    Thread spamThread = new Thread(spam);
    spamThread.setName("spamThread");
    spamThread.start();

    Stopper stopper = new Stopper(spamThread);
    Thread stopperThread = new Thread(stopper);
    stopperThread.setDaemon(true);
    stopperThread.start();

    Thread.sleep(Duration.ofSeconds(5));
    System.setIn(new ByteArrayInputStream(new byte[]{'\n'}));

  }

  private static class Spam implements Runnable {

    private final int[] intervals;
    private final String[] messages;

    public Spam(int[] intervals, String[] messages) {
      this.intervals = intervals;
      this.messages = messages;
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i < intervals.length; i++) {
          Thread.sleep(intervals[i]);
          System.out.println(messages[i]);
        }
      } catch (InterruptedException e) {}
      finally {
        System.out.println(Thread.currentThread().getName() + " is interrupted");
      }
    }
  }

  private static class Stopper implements Runnable {

    private final Thread targetThread;

    private Stopper(Thread targetThread) {
      this.targetThread = targetThread;
    }

    @Override
    public void run() {
      while (true) {
        try {
          if (System.in.available() > 0 && System.in.read() == '\n') {
            System.out.println("interrupting " + targetThread.getName());
            targetThread.interrupt();
          }
        } catch (IOException e) {
        }
      }
    }
  }
}
