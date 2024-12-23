package course.pyatakha.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OldSchoolMultithreadingSolution {

  public static void main(String[] args) {

    int[][] matrix = MatrixCreator.createMatrix(10, 1000);


    List<Thread> threads = new ArrayList<>();
    for (int i = 0; i < matrix.length; i++) {
      threads.add(new Thread(new MaxNumberSearch(matrix[i])));
    }

    threads.forEach(Thread::start);
    System.out.println(MaxNumberSearch.max);

  }

  private static class MaxNumberSearch implements Runnable {
    final int[] numArray;
    static AtomicInteger max = new AtomicInteger();

    private MaxNumberSearch(int[] numArray) {
      this.numArray = numArray;
    }

    @Override
    public void run() {

      try {
        for (int num : numArray) {
          Thread.sleep(1);
          if (max.get() < num) updateMax(num);
        }
      } catch (Exception e) {
      }
    }

    private synchronized void updateMax(int newValue) {
      if (max.get() < newValue) max.set(newValue);
    }
  }
}
