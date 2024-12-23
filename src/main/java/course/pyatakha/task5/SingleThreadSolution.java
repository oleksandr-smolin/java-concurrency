package course.pyatakha.task5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadSolution {

//  public static void main(String[] args) {
//
//    int[][] matrix = MatrixCreator.createMatrix(100000, 10000);
//
//    int max = matrix[0][0];
//    for (int i = 0; i < matrix.length; i++) {
//      for (int j = 0; j < matrix[0].length; j++) {
//        if (max < matrix[i][j]) max = matrix[i][j];
//      }
//    }
//
////    Arrays.stream(matrix).forEach(row -> System.out.println(Arrays.toString(row)));
//    System.out.println("Maximal value: " + max);
//  }


  public static void main(String[] args) throws ExecutionException, InterruptedException {

    int[][] matrix = MatrixCreator.createMatrix(10, 1000);


    ExecutorService executorService = Executors.newSingleThreadExecutor();

    List<CompletableFuture<Integer>> futures = Arrays.stream(matrix)
        .map(row ->
            CompletableFuture.supplyAsync(() -> new SingleThreadSolution.MaxNumberSearch(row).call(), executorService))
        .toList();

//    List<Future<Integer>> futures = Arrays.stream(matrix)
//        .map(row -> executorService.submit(new MaxNumberSearch(row))).toList();


    int max = 0;
    for (Future<Integer> future : futures) {
      int num = future.get();
      if (max < num) max = num;
    }

    System.out.println("features.size(): " + futures.size());
    System.out.println("Max number: " + max);
    executorService.shutdown();


  }

  private static class MaxNumberSearch implements Callable<Integer> {

    private final int[] numArray;

    private MaxNumberSearch(int[] numArray) {
      this.numArray = numArray;
    }

    @Override
    public Integer call() {
      System.out.println("I'm running: " + Thread.currentThread().getName());
      int max = 0;

      try {
        for (int num : numArray) {
          Thread.sleep(1);
          if (max < num) max = num;
        }
      } catch (Exception e) {
      }
      return max;
    }
  }
}
