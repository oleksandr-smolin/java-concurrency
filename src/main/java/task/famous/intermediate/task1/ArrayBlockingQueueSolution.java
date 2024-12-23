package task.famous.intermediate.task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ArrayBlockingQueueSolution {

  private static final ArrayBlockingQueue queue = new ArrayBlockingQueue(10);

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

        produce(numberForPrinting);
      }
    }

    private void produce(int numberForPrinting) {
      try {
        queue.put(numberForPrinting);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println(Thread.currentThread().getName() + " I produced: " + numberForPrinting);
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

        consumeAndPrint();
      }
    }

    private void consumeAndPrint() {
      try {
        System.out.println(Thread.currentThread().getName() + " I consumed: " + queue.take());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
