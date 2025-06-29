package task.famous.basic.task5;

import java.util.stream.IntStream;

public class Solution1 {

    public static void main(String[] args) throws InterruptedException {

        IntStream.rangeClosed(0,4).mapToObj(index -> {
            var thread = new Thread(() -> System.out.println("Hello from: " + Thread.currentThread().getName()));
            thread.setName(String.valueOf(index));
            return thread;
        }).forEach(Thread::start);

        System.out.println("hallo from: " + Thread.currentThread().getName());

    }
}
