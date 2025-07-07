package task.famous.basic.task7;

import java.util.List;
import java.util.stream.IntStream;

public class Solution1 {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> numbers = IntStream.rangeClosed(1, 20).boxed().toList();
        List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0).toList();
        List<Integer> odds = numbers.stream().filter(n -> n % 2 != 0).toList();

        Printer printer = new Printer();
        Worker evensWorker = new Worker(printer, evens);
        Worker oddsWorker = new Worker(printer, odds);

        var thread1 = new Thread(evensWorker);
        var thread2 = new Thread(oddsWorker);


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
