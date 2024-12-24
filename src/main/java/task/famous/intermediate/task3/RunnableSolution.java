package task.famous.intermediate.task3;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableSolution {

  private static final int NUMBER_OF_THREADS = 12; // change to 1 to compare efficiency

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();

    int[][] matrix = new int[100][100];

    try (ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
      Arrays.stream(matrix).forEach(array -> es.submit(new Filler(array)));
    }

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    for (int[] array: matrix) {
      System.out.println(Arrays.toString(array));
    }

    System.out.println("matrix filling duration millis: " + duration);

  }

  private static class Filler implements Runnable {

    private final int[] array;

    private Filler(int[] array) {
      this.array = array;
    }

    @Override
    public void run() {
      for (int i = 0; i < array.length; i++) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        array[i] = (int) (Math.random() * 1000);
      }
    }
  }
}
