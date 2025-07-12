package task.famous.advanced.task1.solution2;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {

    private final int id;
    private final AtomicInteger balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = new AtomicInteger(balance);
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance.get();
    }

    public void debitBalance(int amount) {
        balance.updateAndGet(currentBalance -> currentBalance + amount);
    }

    public void creditBalance(int amount) {
        balance.updateAndGet(currentBalance -> currentBalance - amount);
    }

}
