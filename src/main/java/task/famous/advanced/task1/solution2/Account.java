package task.famous.advanced.task1.solution2;

import java.util.concurrent.atomic.AtomicInteger;

class Account {

    private final int id;
    private final AtomicInteger balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = new AtomicInteger(balance);
    }

    int getId() {
        return id;
    }

    int getBalance() {
        return balance.get();
    }

    void debitBalance(int amount) {
        balance.updateAndGet(currentBalance -> currentBalance + amount);
    }

    void creditBalance(int amount) {
        balance.updateAndGet(currentBalance -> currentBalance - amount);
    }

}
