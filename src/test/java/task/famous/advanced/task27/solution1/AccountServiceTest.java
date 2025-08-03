package task.famous.advanced.task27.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AccountServiceTest {

    @Test
    void should_deposit() {
        AccountService accountService = new AccountService();

        Account account = accountService.getAccounts().stream().findFirst().get();
        BigDecimal balanceBeforeDeposit = account.getBalance();

        InnerAccountMoneyTransferData transferData = new InnerAccountMoneyTransferData(account.getId(), BigDecimal.TEN);

        BigDecimal balanceAfterDeposit = accountService.deposit(transferData);

        Assertions.assertEquals(balanceBeforeDeposit.add(BigDecimal.TEN), balanceAfterDeposit);
    }

    @Test
    void should_deposit_sequentially() {
        AccountService accountService = new AccountService();

        Account account = accountService.getAccounts().stream().findFirst().get();
        BigDecimal balanceBeforeDeposit = account.getBalance();

        List<CompletableFuture<BigDecimal>> completableFutureList = IntStream.rangeClosed(1,10000).mapToObj(i -> CompletableFuture.supplyAsync(() -> {
            BigDecimal amount = BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(100));
            InnerAccountMoneyTransferData transferData = new InnerAccountMoneyTransferData(account.getId(), amount);
            accountService.deposit(transferData);
            return amount;
        })).toList();

        BigDecimal totalDepositAmount = CompletableFuture.allOf(completableFutureList.toArray(CompletableFuture[]::new))
                .thenApply(v -> completableFutureList.stream().map(CompletableFuture::join).filter(Objects::nonNull).toList())
                .join().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        Assertions.assertEquals(balanceBeforeDeposit.add(totalDepositAmount), account.getBalance());
    }
}
