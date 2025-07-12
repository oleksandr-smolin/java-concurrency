package task.famous.advanced.task1.solution2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class AccountTest {

    // more easiest unit tests should be added as well

    @Test
    void multipleTransferShouldNotInterfereEachOther() {
        Account accountA = new Account(1, 10_000_000);
        Account accountB = new Account(2, 10_000_000);
        int amountToTransfer = 10;

        try (ExecutorService es = Executors.newFixedThreadPool(8)) {
            IntStream.range(0, 100_000)
                    .forEach(i -> {
                        if (i % 2 == 0) {
                            es.submit(() -> {
                                accountA.creditBalance(amountToTransfer);
                                accountB.debitBalance(amountToTransfer);
                            });
                        } else {
                            es.submit(() -> {
                                accountB.creditBalance(amountToTransfer);
                                accountA.debitBalance(amountToTransfer);
                            });
                        }
                    });
        }

        Assertions.assertEquals(10_000_000, accountA.getBalance());
        Assertions.assertEquals(10_000_000, accountB.getBalance());
    }
}
