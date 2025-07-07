package task.famous.basic.task6;

public class AtomicIntSolution {

    public static void main(String[] args) throws InterruptedException {

        BankAccount bankAccount = new BankAccount(500);

        Thread userThread1 = new Thread(new User(bankAccount));
        Thread userThread2 = new Thread(new User(bankAccount));

        userThread1.start();
        userThread2.start();

        userThread1.join();
        userThread2.join();

        System.out.println(Thread.currentThread().getName() + " work finished");
    }
}
