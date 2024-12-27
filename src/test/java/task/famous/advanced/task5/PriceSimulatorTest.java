package task.famous.advanced.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Phaser;

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
}
