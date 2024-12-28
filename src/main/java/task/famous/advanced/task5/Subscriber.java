package task.famous.advanced.task5;

import java.util.List;
import java.util.concurrent.Phaser;

public class Subscriber implements Runnable {

  private final Phaser phaser;
  private final List<StockState> stockStateList;

  public Subscriber(Phaser phaser, List<StockState> stockStateList) {
    this.phaser = phaser;
    this.stockStateList = stockStateList;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted() && !phaser.isTerminated()) {
      phaser.arriveAndAwaitAdvance();
      StockState stockState = stockStateList.get(phaser.getPhase() -1);

      System.out.println(Thread.currentThread().getName() + "I have consumed: " + stockState.toString());
    }
  }
}
