package task.famous.basic.task4;

import java.util.List;
import java.util.stream.IntStream;

public class Solution1 {

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threadList = IntStream.rangeClosed(0,4).mapToObj(index -> {
            var thread = new Thread(() -> System.out.println("Hello from: " + Thread.currentThread().getName()));
            thread.setName(String.valueOf(index));
            return thread;
        }).toList();


        threadList.forEach(Thread::start);
        for (Thread thread : threadList) thread.join();


        System.out.println("hallo from: " + Thread.currentThread().getName());

    }
}
