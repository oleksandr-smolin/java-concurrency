package task.famous.advanced.task1.solution4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BankTest {

    @Test
    void shouldTransferMoney() {
        Bank bank = new Bank();

        var accountList = new ArrayList<>(bank.getAccountMap().values());

        Account sender = accountList.get(0);
        BigDecimal senderBalanceBeforeTransfer = sender.balance();
        Account receiver = accountList.get(1);
        BigDecimal receiverBalanceBeforeTransfer = receiver.balance();

        var transferDetails = new TransferDetails(sender.id(), receiver.id(), BigDecimal.TEN);

        bank.transferMoney(transferDetails);

        Assertions.assertEquals(senderBalanceBeforeTransfer.subtract(BigDecimal.TEN), sender.balance());
        Assertions.assertEquals(receiverBalanceBeforeTransfer.add(BigDecimal.TEN), receiver.balance());
    }

    @Test
    void shouldTransferMoneyThreadSafety() {
        Bank bank = new Bank();

        var accountList = new ArrayList<>(bank.getAccountMap().values());

        var account1 = accountList.get(0);
        BigDecimal senderBalanceBeforeTransfer = account1.balance();
        var account2 = accountList.get(1);
        BigDecimal receiverBalanceBeforeTransfer = account2.balance();

        long start = System.nanoTime();

        List<CompletableFuture<TransferDetails>> completableFutureList = IntStream.rangeClosed(1, 50_000)
                .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                    int amount = ThreadLocalRandom.current().nextInt(i);

                    var transferDetails =  amount % 2 == 0
                            ? new TransferDetails(account1.id(), account2.id(), new BigDecimal(amount))
                            : new TransferDetails(account2.id(), account1.id(), new BigDecimal(amount));

                    bank.notifySenderAboutLackOfBudget(transferDetails.senderAccountId());

                    return bank.transferMoney(transferDetails) ? transferDetails : null;
                })).toList();

        List<TransferDetails> transferDetailsList = CompletableFuture.allOf(completableFutureList.toArray(CompletableFuture[]::new))
                .thenApply(v -> completableFutureList.stream().map(CompletableFuture::join).filter(Objects::nonNull).toList())
                .join();

        long end = System.nanoTime();
        long durationMillis = (end - start) / 1_000_000;
        System.out.println("Transfers completed in " + durationMillis + " ms");

        for (var transferDetails : transferDetailsList) {
            if (account1.id().equals(transferDetails.senderAccountId())) {
                senderBalanceBeforeTransfer = senderBalanceBeforeTransfer.subtract(transferDetails.amount());
                receiverBalanceBeforeTransfer = receiverBalanceBeforeTransfer.add(transferDetails.amount());
            } else {
                senderBalanceBeforeTransfer = senderBalanceBeforeTransfer.add(transferDetails.amount());
                receiverBalanceBeforeTransfer = receiverBalanceBeforeTransfer.subtract(transferDetails.amount());
            }
        }

        Assertions.assertEquals(senderBalanceBeforeTransfer, account1.balance());
        Assertions.assertEquals(receiverBalanceBeforeTransfer, account2.balance());
    }

}

