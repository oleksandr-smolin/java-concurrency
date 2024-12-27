package task.famous.advanced.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

public class PriceSimulator {

  private static final int NUMBER_OF_THREADS = 4;
  private final List<StockState> stockStateList;
  private final Phaser phaser;

  public PriceSimulator(List<StockState> stockStateList, Phaser phaser) {
    this.stockStateList = stockStateList;
    this.phaser = phaser;
  }

  public static void main(String[] args) {
    new PriceSimulator(Collections.synchronizedList(new ArrayList<>()), new Phaser(NUMBER_OF_THREADS)).start();
  }

  public void start() {
    final var es = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    es.submit(new StockMarket(phaser, stockStateList));
    IntStream.rangeClosed(0, NUMBER_OF_THREADS -1).forEach(i -> es.submit(new Subscriber(phaser, stockStateList)));
  }
}
