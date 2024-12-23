package task.famous.intermediate.task2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class Table {

  private static final int NUMBER_OF_PHILOSOPHERS = 5;

  public static void main(String[] args) {

    List<Chopstick> chopstickList = IntStream.range(1, NUMBER_OF_PHILOSOPHERS + 1).mapToObj(Chopstick::new).toList();
    final var semaphore = new Semaphore(chopstickList.size()-1, true);
    List<Philosopher> philosophers = IntStream.range(0, chopstickList.size())
        .mapToObj(i -> new Philosopher(semaphore, chopstickList.get(i%chopstickList.size()), chopstickList.get((i+1)%chopstickList.size()))).toList();

    try(ExecutorService es = Executors.newFixedThreadPool(chopstickList.size())) {
      philosophers.forEach(es::submit);
    }

  }
}
