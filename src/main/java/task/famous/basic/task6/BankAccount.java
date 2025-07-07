package task.famous.basic.task6;

import java.util.concurrent.atomic.AtomicInteger;

class BankAccount {

    private final AtomicInteger balance;

    BankAccount(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    int getBalance() {
        return balance.get();
    }

    int deposit(int depositAmount) {
        return balance.addAndGet(depositAmount);
    }

    int withdraw(int withdrawAmount) throws IllegalArgumentException {

        boolean isBalanceUpdated;
        int sum;
        int currentBalance;

         do {
            currentBalance = balance.get();
            sum = currentBalance - withdrawAmount;
            if (sum < 0) {
                throw new IllegalArgumentException(Thread.currentThread().getName()
                        + ": withdrawing such amount of money is not possible: " + withdrawAmount
                        + " currentBalance" + currentBalance);
            }

            isBalanceUpdated = balance.compareAndSet(currentBalance, sum);
        } while (!isBalanceUpdated);

         return sum;
    }
}
