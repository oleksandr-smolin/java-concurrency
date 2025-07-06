package task.famous.basic.task5;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Solution1 {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws Exception {

        List<Integer> intList = IntStream.rangeClosed(0, 9).map(num -> RANDOM.nextInt(500)).boxed().toList();

        var worker1 = new Worker(intList.subList(0,4));
        var worker2 = new Worker(intList.subList(5,9));

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = executor.submit(worker1);
        Future<Integer> future2 = executor.submit(worker2);

        System.out.println("I'm submitted tasks");

        int result1 = future1.get();
        int result2 = future2.get();

        System.out.println(result1 + result2);
        System.out.println("I'm finished");

        executor.shutdown();
    }
}
