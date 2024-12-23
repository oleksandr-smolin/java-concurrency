package task.famous.basic.task1;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

public class ReentrantLockSolution {

  private final static Lock lock = new java.util.concurrent.locks.ReentrantLock();
  private final static Condition condition = lock.newCondition();

  private static int counter = 0;

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();

    printConcurrently();

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    System.out.println("duration millis: " + duration);
  }

  private static void printConcurrently() {
    List<Thread> threadList = IntStream.range(0, 12)
        .mapToObj(i -> new Thread(() -> ReentrantLockSolution.print(Thread.currentThread().getName(), i))).toList();

    threadList.forEach(Thread::start);
    threadList.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
  }

  private static void print(String ThreadName, int threadIndex) {
    try {
      lock.lock();

      while (counter != threadIndex) {
        condition.await();
      }

      System.out.println(ThreadName);
      counter++;
      condition.signalAll();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }
}
