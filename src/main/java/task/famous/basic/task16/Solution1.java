package task.famous.basic.task16;

import java.util.concurrent.ForkJoinPool;

public class Solution1 {

    public static void main(String[] args) {

        long result = 0;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            result = pool.invoke(new ComputeTask(25,1));
        }

        System.out.println(result);
    }
}
