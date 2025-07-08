package task.famous.basic.task8;

import java.util.Random;

public class Solution1 {

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + ": do small job");
            }
        });

        Thread interrupter = new Thread(() -> {
            try {
                Thread.sleep(new Random().nextInt(2000));
                worker.interrupt();
                System.out.println(Thread.currentThread().getName() + ": worker interrupted");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        worker.start();
        interrupter.start();

        worker.join();
        interrupter.join();
        System.out.println(Thread.currentThread().getName() + ": work finished");
    }
}
