package course.pyatakha.task6;

import java.util.concurrent.atomic.AtomicInteger;

public class OldSchoolMultithreadingSolution {

  private static final AtomicInteger COUNTER = new AtomicInteger(3);
  private static final Object MONITOR = new Object();

  public static void main(String[] args) {

    final StringBuffer stringBuffer = new StringBuffer();

    Thread writer = new Thread(new Writer(stringBuffer));

    Thread reader1 = new Thread(new Reader(stringBuffer));
    reader1.setName("reader1");
    Thread reader2 = new Thread(new Reader(stringBuffer));
    reader2.setName("reader2");
    Thread reader3 = new Thread(new Reader(stringBuffer));
    reader3.setName("reader3");

    reader1.start();
    reader2.start();
    reader3.start();
    writer.start();

  }

  private static class Writer implements Runnable {

    private final StringBuffer stringBuffer;

    private Writer(StringBuffer stringBuffer) {
      this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
      while (true) {
        synchronized (MONITOR) {
          if (COUNTER.get() == 0) {

            stringBuffer.setLength(0);

            for (int i = 0; i < 5; i++) {
              int randomNum = 97 + (int) (Math.random() * (122 - 97 + 1));
              stringBuffer.append((char) randomNum);
            }
            System.out.println("I Wrote: " + stringBuffer);
            COUNTER.set(3);
            MONITOR.notifyAll();
          }
        }
      }
    }
  }

  private static class Reader implements Runnable {
    private final StringBuffer stringBuffer;

    private Reader(StringBuffer stringBuffer) {
      this.stringBuffer = stringBuffer;
    }

    @Override
    public void run() {
      while (true) {
        System.out.println("I'm " + Thread.currentThread().getName() + " and I read: " + stringBuffer);
        synchronized (MONITOR) {
          if (COUNTER.decrementAndGet() == 0) MONITOR.notifyAll();

          try {
            MONITOR.wait();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      }
    }
  }
}
