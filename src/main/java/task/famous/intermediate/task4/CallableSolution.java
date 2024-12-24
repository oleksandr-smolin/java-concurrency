package task.famous.intermediate.task4;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CallableSolution {

  private static final int NUMBER_OF_THREADS = 12;
  private static final int NUMBER_OF_INTEGERS = 16_000;
  private static final int TARGET_INDEX = (int) (NUMBER_OF_INTEGERS * 0.8);
  private static final int NUMBER_OF_ELEMENTS_IN_PARTITION = NUMBER_OF_INTEGERS / NUMBER_OF_THREADS;

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();

    var random = new Random();

    int[] hugeArray = IntStream.range(0, NUMBER_OF_INTEGERS).map(i -> random.nextInt(100_000)).toArray();

    int targetNum = random.nextInt(100_000_000) + 100_000;
    System.out.println("targetNum: " + targetNum);

    System.out.println("targetIndex: " + TARGET_INDEX);
    System.out.println();

    hugeArray[TARGET_INDEX] = targetNum;

    int result;
    try (ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
      result = es.invokeAny(IntStream.range(0, NUMBER_OF_THREADS)
          .mapToObj(threadIndex -> {
            int startRange = threadIndex * NUMBER_OF_ELEMENTS_IN_PARTITION;
            int endRange = Math.min(startRange + NUMBER_OF_ELEMENTS_IN_PARTITION, hugeArray.length);
            return new Searcher(hugeArray, startRange, endRange, targetNum);
          }).toList());

    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }


    System.out.println("found targetNum on index: " + result);

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    System.out.println();
    System.out.println("matrix filling duration millis: " + duration);
  }

  private static class Searcher implements Callable<Integer> {
    private final int[] array;
    private final int startRange;
    private final int endRange;
    private final int targetNum;

    private Searcher(int[] array, int startRange, int endRange, int targetNum) {
      this.array = array;
      this.startRange = startRange;
      this.endRange = endRange;
      this.targetNum = targetNum;
    }

    @Override
    public Integer call() {
      for (int i = startRange; !Thread.currentThread().isInterrupted() && i < endRange; i++) {
        {
          // calculation imitation instead of Thread.sleep(1) which takes approximately same time
          // to give us opportunity avoid interruption while it sleeps
          new Random().ints(40_000).forEach(num -> {});
        }

        if (array[i] == targetNum) {
          return i;
        }
      }
      return null;
    }
  }
}
