package task.famous.advanced.task1.solution4;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * solution via java.util.concurrent.locks.ReentrantLock
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

        try {
            if (sameOrder) {
                senderAccount.getLock().lock();
                receiverAccount.getLock().lock();
            } else {
                receiverAccount.getLock().lock();
                senderAccount.getLock().lock();
            }

            if (senderAccount.balance().compareTo(transferDetails.amount()) < 0) {
                return false;
            }

            ThreadLocalRandom.current().ints(500_000).forEach(__ -> {});

            senderAccount.setBalance(senderAccount.balance().subtract(transferDetails.amount()));
            receiverAccount.setBalance(receiverAccount.balance().add(transferDetails.amount()));

        } finally {
            senderAccount.getLock().unlock();
            receiverAccount.getLock().unlock();
        }

        return true;
    }

    void notifySenderAboutLackOfBudget(UUID accountId) {
        Account account = accountMap.get(accountId);

        try {
            account.getLock().lock();
            ThreadLocalRandom.current().ints(500_000).forEach(__ -> {}); // imitate notification
        } finally {
            account.getLock().unlock();
        }
    }
}
