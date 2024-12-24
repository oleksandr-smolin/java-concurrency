package task.famous.intermediate.task6;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ForkJoinSolution {

  public static void main(String[] args) {

    int[] array = IntStream.range(0, 100_000_000).map(i -> (int) (Math.random() * 1000)).toArray();

    long startTime = System.currentTimeMillis();

    long result;

    {
      // single thread solution
      // result = Arrays.stream(array).asLongStream().peek(__-> new Random().ints(40_000).forEach(___-> {})).sum();
    }

    {
      // multithreading solution
      try (final var joinPool = ForkJoinPool.commonPool()) {
        result = joinPool.invoke(new RecursiveSumTask(array, 0, array.length));
      }
    }

    System.out.println(result);

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    System.out.println();
    System.out.println("duration millis: " + duration);
  }

  private static class RecursiveSumTask extends RecursiveTask<Long> {

    private static final int MAX_RANGE = 100_000;

    private final int[] array;
    private final int startRange;
    private final int endRange;

    private RecursiveSumTask(int[] array, int startRange, int endRange) {
      this.array = array;
      this.startRange = startRange;
      this.endRange = endRange;
    }

    @Override
    protected Long compute() {
      if (endRange - startRange > MAX_RANGE) {
        return ForkJoinTask.invokeAll(createSubTasks()).stream().mapToLong(ForkJoinTask::join).sum();
      } else {
        return sum();
      }
    }

    private Long sum() {
      long temp = 0;
      for (int i = startRange; i < endRange; i++) {
        ThreadLocalRandom.current().ints(40_000).forEach(__ -> {});
        temp += array[i];
      }
      return temp;
    }

    private Collection<RecursiveSumTask> createSubTasks() {
      int median = (startRange + endRange) / 2;

      return List.of(
          new RecursiveSumTask(array, startRange, median),
          new RecursiveSumTask(array, median, endRange)
      );
    }
  }
}
