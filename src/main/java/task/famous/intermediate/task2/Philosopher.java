package task.famous.intermediate.task2;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

  private final Semaphore semaphore;
  private final Chopstick leftChopstick;
  private final Chopstick rightChopstick;

  public Philosopher(Semaphore semaphore, Chopstick leftChopstick, Chopstick rightChopstick) {
    this.semaphore = semaphore;
    this.leftChopstick = leftChopstick;
    this.rightChopstick = rightChopstick;
  }

  @Override
  public void run() {
    while (true) {
      think();
      eat();
    }
  }

  private void think() {
    try {
      Thread.sleep((int)(Math.random() * 2000) + 1000);
      System.out.println(Thread.currentThread().getName() + " I have finished thinking");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void eat() {
    try {
      semaphore.acquire();
      synchronized (leftChopstick) {
        synchronized (rightChopstick) {
          Thread.sleep((int)(Math.random() * 2000) + 1000);
          System.out.println(Thread.currentThread().getName() + " I have finished eating"
              + " I used: leftChopstick - " + leftChopstick.id() + " and rightChopstick " + rightChopstick.id());
        }
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      semaphore.release();
    }
  }
}
