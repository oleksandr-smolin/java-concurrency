package task.famous.intermediate.task5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class FutureSolution {

  private static final int NUMBER_OF_THREADS = 12;

  private static final String SOURCE_FILE = "src/main/java/task/famous/intermediate/task5/war_and_peace.txt";


  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();

    int result;
    try (final var reader = new BufferedReader(new FileReader(SOURCE_FILE));
         final var es = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {

      List<ReadTask> readTasks = IntStream.range(0, NUMBER_OF_THREADS)
          .mapToObj(i -> new ReadTask(reader)).toList();

      List<Future<Integer>> futureList = es.invokeAll(readTasks);

      result = futureList.stream().map(future -> {
            try {
              new Random().ints(100_000_000).forEach(num -> {});
              return future.get();
            } catch (InterruptedException | ExecutionException e) {
              throw new RuntimeException(e);
            }
          })
          .reduce(Integer::sum).get();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("number of words in book: " + result);

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    System.out.println("all lines read for millis: " + duration);

  }

  private static class ReadTask implements Callable<Integer> {

    private final BufferedReader reader;

    private ReadTask(BufferedReader reader) {
      this.reader = reader;
    }

    @Override
    public Integer call() throws Exception {
      String line;
      int count = 0;
      while ((line = reader.readLine()) != null) {
        new Random().ints(40_000).forEach(num -> {});
        count += line.split("\\s").length;
      }
      return count;
    }
  }
}
