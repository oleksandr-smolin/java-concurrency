package task.famous.basic.task9;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Solution1 {

    private static final int NUMBER_OF_THREADS = 5;
    private static final CountDownLatch startingGun = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threadList = IntStream.rangeClosed(0, NUMBER_OF_THREADS)
                .mapToObj(i -> new Thread(Solution1::run)).toList();

        threadList.forEach(Thread::start);
        startingGun.countDown();
        for (Thread thread : threadList) thread.join();

        System.out.println(Thread.currentThread().getName() + " All done!");
    }

    private static void run() {
        startingGun.countDown();
        try {
            startingGun.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " hello");
    }

    private void printHello() {
        System.out.println("hello");
    }

}
