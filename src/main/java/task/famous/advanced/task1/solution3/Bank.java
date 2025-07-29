package task.famous.advanced.task1.solution3;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * solution via Synchronization
 */

class Bank {

    private final Map<UUID, Account> accountMap;

    Map<UUID, Account> getAccountMap() {
        return accountMap;
    }

    Bank() {
        accountMap = IntStream.range(0, 2).mapToObj(i ->
                        new Account(UUID.randomUUID(), new BigDecimal(1_000)))
                .collect(Collectors.toConcurrentMap(Account::id, Function.identity()));
    }

    boolean transferMoney(TransferDetails transferDetails) {
        Account senderAccount = accountMap.get(transferDetails.senderAccountId());
        Account receiverAccount = accountMap.get(transferDetails.receiverAccountId());

        boolean sameOrder = senderAccount.id().compareTo(receiverAccount.id()) < 0;

        Object firstLock = sameOrder ? senderAccount : receiverAccount;
        Object secondLock = !sameOrder ? receiverAccount : senderAccount;

        synchronized (firstLock) {
            synchronized (secondLock) {

                if (senderAccount.balance().compareTo(transferDetails.amount()) < 0) {
                    return false;
                }

                ThreadLocalRandom.current().ints(500_000).forEach(__ -> {});

                senderAccount.setBalance(senderAccount.balance().subtract(transferDetails.amount()));
                receiverAccount.setBalance(receiverAccount.balance().add(transferDetails.amount()));
            }
        }

        return true;
    }

    void notifySenderAboutLackOfBudget(UUID accountId) {
        var account = accountMap.get(accountId);

        synchronized (account) {
            ThreadLocalRandom.current().ints(500_000).forEach(__ -> {}); // imitate notification
        }
    }
}
