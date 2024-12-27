package task.famous.advanced.task5;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class StockMarket implements Runnable {

  private final Phaser phaser;
  private final List<StockState> stockStateList;

  public StockMarket(Phaser phaser, List<StockState> stockStateList) {
    this.phaser = phaser;
    this.stockStateList = stockStateList;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      Stock apple = new Stock(1, "Apple", new BigDecimal(ThreadLocalRandom.current().nextInt(1000)));
      Stock microsoft = new Stock(2, "Microsoft", new BigDecimal(ThreadLocalRandom.current().nextInt(1000)));
      Stock google = new Stock(3, "Google", new BigDecimal(ThreadLocalRandom.current().nextInt(1000)));

      final var stockState = new StockState(List.of(apple, microsoft, google), LocalDateTime.now());
      stockStateList.add(stockState);
      phaser.arriveAndAwaitAdvance();
    }
  }
}
