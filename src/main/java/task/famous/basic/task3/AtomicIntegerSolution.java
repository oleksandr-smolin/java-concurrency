package task.famous.basic.task3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerSolution {

  public static void main(String[] args) throws InterruptedException {

    var counter = new SynchronizedCounter();

    Thread incrementer = new Thread(() -> IntStream.range(0, 10000).forEach(i -> counter.increment()));
    Thread decrementer = new Thread(() -> IntStream.range(0, 10000).forEach(i -> counter.decrement()));

    incrementer.start();
    decrementer.start();

    incrementer.join();
    decrementer.join();


    System.out.println(counter.getValue());

  }

  public static class SynchronizedCounter {
    private AtomicInteger count = new AtomicInteger();

    public void increment() {
      count.incrementAndGet();
    }

    public void decrement() {
      count.decrementAndGet();
    }

    public int getValue() {
      return count.get();
    }
  }
}
