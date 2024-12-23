package task.famous.intermediate.task1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class SemaphoreSolution {

  private static final int QUEUE_SIZE = 10;
  private static final Queue<Integer> queue = new ArrayDeque<>(QUEUE_SIZE);
  private static final Semaphore writeSemaphore = new Semaphore(QUEUE_SIZE);
  private static final Semaphore readSemaphore = new Semaphore(0);

  public static void main(String[] args) {

    try(var producerEs = Executors.newFixedThreadPool(4);
        var consumerEs = Executors.newFixedThreadPool(4)) {
      IntStream.range(0, 4).forEach(i -> producerEs.submit(new Producer()));
      IntStream.range(0, 2).forEach(i -> consumerEs.submit(new Consumer()));
    }
  }
  private static class Producer implements Runnable {

    @Override
    public void run() {

      while(true) {
        int numberForPrinting = (int) (Math.random() * 1000);

        try {
          writeSemaphore.acquire();
          produce(numberForPrinting);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          readSemaphore.release();
        }
      }
    }

    private void produce(int numberForPrinting) {
      synchronized (queue) {
        queue.offer(numberForPrinting);
        System.out.println(Thread.currentThread().getName() + " I produced: " + numberForPrinting);
      }
    }
  }

  private static class Consumer implements Runnable {
    @Override
    public void run() {

      while(true) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        try {
          readSemaphore.acquire();
          consumeAndPrint();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        } finally {
          writeSemaphore.release();
        }
      }
    }

    private void consumeAndPrint() {
      synchronized (queue) {
        System.out.println(Thread.currentThread().getName() + " I consumed: " + queue.poll());
      }
    }
  }
}
