package task.famous.intermediate.task7;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CountDownLatchSolution {

  private static final int NUMBER_OF_THREADS = 12;
  private static final CountDownLatch startingGun = new CountDownLatch(1);
  private static final Queue<String> raceResultsQueue = new  ConcurrentLinkedQueue<>();

  public static void main(String[] args) throws InterruptedException {

    try(final var es = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
      IntStream.range(0,NUMBER_OF_THREADS).mapToObj(i -> new Racer()).forEach(es::submit);

      System.out.println("Ready");
      Thread.sleep(2000);
      System.out.println("Steady");
      Thread.sleep(2000);
      System.out.println("Go!!!");
      System.out.println();
      startingGun.countDown();
    }

    System.out.println("Last racer has finished");
    System.out.println();

    System.out.println("Winner is: " + raceResultsQueue.peek());
    System.out.println("Results: \n" + Arrays.toString(raceResultsQueue.toArray()));
  }

  private static class Racer implements Runnable {

    @Override
    public void run() {
      try {
        startingGun.await();
        Thread.sleep((long) (Math.random() * 1500) + 1000);
        raceResultsQueue.add(Thread.currentThread().getName());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
