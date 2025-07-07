package task.famous.basic.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankAccountTest {

    @Test
    void depositTest() {
        BankAccount bankAccount = new BankAccount(200);
        int currentBalance = bankAccount.deposit(100);

        Assertions.assertEquals(300, currentBalance);
    }

    @Test
    void multipleThreadDeposit() {
        var bankAccount = new BankAccount(0);
        var random = new Random();

        int resultSum = 0;

        try (ExecutorService es = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 100; i++) {
                int randomAmount = random.nextInt(100);
                resultSum += randomAmount;
                es.submit(() -> bankAccount.deposit(randomAmount));
            }
        }

        Assertions.assertEquals(resultSum, bankAccount.getBalance());
    }

    @Test
    void withdraw() {
        BankAccount bankAccount = new BankAccount(200);
        int currentBalance = bankAccount.withdraw(100);

        Assertions.assertEquals(100, currentBalance);
    }

    @Test
    void multipleThreadWithdraw() {
        int balance = 100000000;
        var bankAccount = new BankAccount(balance);
        var random = new Random();

        try (ExecutorService es = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 100; i++) {
                int randomAmount = random.nextInt(100);
                balance -= randomAmount;
                es.submit(() -> bankAccount.withdraw(randomAmount));
            }
        }

        Assertions.assertEquals(balance, bankAccount.getBalance());
    }

    @Test
    void multipleThreadsDepositAndWithdrawRandomly() {
        int balance = 100000000;
        var bankAccount = new BankAccount(balance);
        var random = new Random();

        try (ExecutorService es = Executors.newFixedThreadPool(50)) {
            List<Runnable> taskList = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                int randomAmount = random.nextInt(100);

                if (randomAmount %2 == 0) {
                    balance += randomAmount;
                    taskList.add(() -> bankAccount.deposit(randomAmount));
                } else {
                    balance -= randomAmount;
                    taskList.add(() -> bankAccount.withdraw(randomAmount));
                }
            }

            taskList.forEach(es::execute);
        }

        Assertions.assertEquals(balance, bankAccount.getBalance());
    }


}
