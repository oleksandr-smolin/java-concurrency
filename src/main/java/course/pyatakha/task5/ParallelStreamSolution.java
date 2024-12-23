package course.pyatakha.task5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParallelStreamSolution {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    int[][] matrix = MatrixCreator.createMatrix(10, 1000);

    List<Integer> maxNumPerRow = Arrays.stream(matrix).parallel().map(ParallelStreamSolution::findMaxInArray).toList();

    int max = 0;
    for (int num : maxNumPerRow) {
      if (max < num) max = num;
    }

    System.out.println("features.size(): " + maxNumPerRow.size());
    System.out.println("Max number: " + max);

  }

  private static int findMaxInArray(int[] row) {
    int max = 0;
    try {
      for (int num : row) {
        Thread.sleep(1);
        if (max < num) max = num;
      }
    } catch (Exception e) {
    }
    return max;
  }
}
