package task.famous.basic.task7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class Printer {

    private final static Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private final static Condition condition = lock.newCondition();

    int counter = 0;

    void print(int num) {
        lock.lock();
        while (num != counter + 1) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(num);

        counter++;
        condition.signalAll();
        lock.unlock();
    }
}
