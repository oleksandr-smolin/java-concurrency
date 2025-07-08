package task.famous.basic.task10;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Solution1 {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        try (ExecutorService es = Executors.newFixedThreadPool(10)) {
            List<Runnable> runnableList = IntStream.range(0, 11)
                    .mapToObj(i -> (Runnable)(() -> System.out.println(RANDOM.nextInt(Integer.MAX_VALUE -1))))
                    .toList();

            runnableList.forEach(es::submit);
        }
    }
}
