package course.pyatakha.task5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FeatureMultithreadingSolution {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    int[][] matrix = MatrixCreator.createMatrix(10, 1000);

    ExecutorService executorService = Executors.newCachedThreadPool();

    List<Future<Integer>> futures = Arrays.stream(matrix)
        .map(row -> executorService.submit(new MaxNumberSearch(row))).toList();


    int max = 0;
    for(Future<Integer> future : futures){
      int num = future.get();
      if(max < num) max = num;
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

//  private synchronized void updateMax(int newValue) {
//    if (max.get() < newValue) max.set(newValue);
//  }
}
