package task.famous.basic.task6;

import java.util.Random;

class User implements Runnable {

    private static final Random RANDOM = new Random();


    private final BankAccount bankAccount;

    User(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int moneyForAction = RANDOM.nextInt(100);

            if (moneyForAction % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " deposit amount: " + moneyForAction);
                int currentBalance = bankAccount.deposit(moneyForAction);
                System.out.println(Thread.currentThread().getName() + " current balance after deposit: " + currentBalance);
            } else {
                System.out.println(Thread.currentThread().getName() + " withdraw amount: " + moneyForAction);
                int currentBalance;
                try {
                    currentBalance = bankAccount.withdraw(moneyForAction);
                } catch (IllegalArgumentException ex) {
                    System.out.println(Thread.currentThread().getName() + " not enough money on balance ");
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + " current balance after withdraw: " + currentBalance);
            }
        }
    }
}
