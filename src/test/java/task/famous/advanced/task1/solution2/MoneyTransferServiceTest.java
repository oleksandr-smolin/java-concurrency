package task.famous.advanced.task1.solution2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MoneyTransferServiceTest {

    static final Random RANDOM = new Random();

    @Test
    void shouldTransferMoneyFromOneAccountToAnotherOne() throws NotEnoughMoney {
        Account accountA = new Account(1, 10);
        Account accountB = new Account(2, 20);

        AccountTransfer accountTransfer = new AccountTransfer(accountA, accountB, 5);
        MoneyTransferService moneyTransferService = new MoneyTransferService();

        moneyTransferService.transferMoney(accountTransfer);

        Assertions.assertEquals(5, accountA.getBalance());
        Assertions.assertEquals(25, accountB.getBalance());
    }

    @Test
    void shouldThrowNotEnoughMoneyException() {
        Account accountA = new Account(1, 10);
        Account accountB = new Account(2, 20);

        AccountTransfer accountTransfer = new AccountTransfer(accountA, accountB, 20);
        MoneyTransferService moneyTransferService = new MoneyTransferService();

        Assertions.assertThrows(NotEnoughMoney.class, () -> {
            moneyTransferService.transferMoney(accountTransfer);
        });
    }

}
