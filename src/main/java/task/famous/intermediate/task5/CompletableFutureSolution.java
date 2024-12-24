package task.famous.intermediate.task5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CompletableFutureSolution {

  private static final int NUMBER_OF_THREADS = 12;

  private static final String SOURCE_FILE = "src/main/java/task/famous/intermediate/task5/war_and_peace.txt";


  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();

    int result = processFile();

    System.out.println("number of words in book: " + result);

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    System.out.println("all lines read for millis: " + duration);

  }

  private static int processFile() {
    try (var reader = new BufferedReader(new FileReader(SOURCE_FILE));
         var es = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {

      List<CompletableFuture<Integer>> futureList = runReaderTasks(reader, es);

      return combineResults(futureList).join();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static List<CompletableFuture<Integer>> runReaderTasks(BufferedReader reader, ExecutorService es) {
    return IntStream.range(0, NUMBER_OF_THREADS)
        .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
          try {
            return new ReadTask(reader).call();
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }, es))
        .toList();
  }

  private static CompletableFuture<Integer> combineResults(List<CompletableFuture<Integer>> futureList) {
    return CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
        .thenApplyAsync(v -> {
          new Random().ints(100_000_000).forEach(num -> {});
          return futureList.stream()
              .map(CompletableFuture::join)
              .reduce(0, Integer::sum);
        });
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
        new Random().ints(40_000).forEach(num -> {
        });
        count += line.split("\\s").length;
      }
      return count;
    }
  }
}
