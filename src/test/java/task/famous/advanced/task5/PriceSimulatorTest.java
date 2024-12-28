package task.famous.advanced.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

public class PriceSimulatorTest {

  @Test
  void testStockMarketSimulation() {
    List<StockState> stockStateList = Collections.synchronizedList(new ArrayList<>());
    Phaser phaser = new Phaser(4);

    new PriceSimulator(stockStateList, phaser).start();

    phaser.register();
    phaser.arriveAndAwaitAdvance();

    Assertions.assertFalse(stockStateList.isEmpty());
  }

  @Test
  void testHighLoad() {
    final int threadNumber = 10;

    final Phaser phaser = new Phaser(threadNumber + 2);
    List<StockState> stockStateList = Collections.synchronizedList(new ArrayList<>(10));


    final int[] stockSize = new int[1];
    try(final var es = Executors.newFixedThreadPool(threadNumber + 2)) {
      es.submit(new StockMarket(phaser, stockStateList));

      IntStream.range(0, threadNumber).forEach(i -> es.submit(new Subscriber(phaser, stockStateList)));


      es.submit(() -> {
        while (phaser.getPhase() < 10) {
          phaser.arriveAndAwaitAdvance();
        }
        stockSize[0] = phaser.getPhase();
        phaser.forceTermination();
      });
    }

    Assertions.assertNotNull(stockStateList.get(stockSize[0]-1));
  }
}
