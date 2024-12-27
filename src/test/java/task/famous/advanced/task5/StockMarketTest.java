package task.famous.advanced.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Phaser;

public class StockMarketTest {


  @Test
  void testStockMarketProduceStockStates() throws InterruptedException {
    final List<StockState> stokStateList = Collections.synchronizedList(new ArrayList<>());
    final Phaser phaser = new Phaser(2);

    final StockMarket stockMarket = new StockMarket(phaser, stokStateList);
    final var threadThread = new Thread(stockMarket);
    threadThread.start();

    final var subscriberThread = new Thread(() -> {
      while (phaser.getPhase() < 10)
        phaser.arriveAndAwaitAdvance();
    });

    subscriberThread.start();
    subscriberThread.join();
    Assertions.assertEquals(11, stokStateList.size());
  }
}
