package task.famous.advanced.task1.solution5;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;
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

        var accountList = senderAccount.id().compareTo(receiverAccount.id()) < 0
                ? List.of(senderAccount, receiverAccount) : List.of(receiverAccount, senderAccount);

        accountList.stream().map(Account::getLock).forEach(StampedLock::writeLock);

//        var stamps = accountList.stream().map(Account::getLock).map(StampedLock::readLock).toList();

        try {
            if (senderAccount.balance().compareTo(transferDetails.amount()) < 0) {
                return false;
            }

//            IntStream.range(0, 1).forEach(i -> accountList.get(i).getLock().tryConvertToWriteLock(stamps.get(i)));

            ThreadLocalRandom.current().ints(500_000).forEach(__ -> {});

            senderAccount.setBalance(senderAccount.balance().subtract(transferDetails.amount()));
            receiverAccount.setBalance(receiverAccount.balance().add(transferDetails.amount()));

        } finally {
//            IntStream.range(0, 1).forEach(i -> accountList.get(i).getLock().unlock(stamps.get(i)));
            accountList.stream().map(Account::getLock).forEach(i -> i.asWriteLock().unlock());
        }

        return true;
    }

    void notifySenderAboutLackOfBudget(UUID accountId) {
        var account = accountMap.get(accountId);

        long stamp = account.getLock().tryOptimisticRead();

        if (!account.getLock().validate(stamp)) {
            stamp = account.getLock().readLock();
            try {
                ThreadLocalRandom.current().ints(500_000).forEach(__ -> {}); // imitate notification
            } finally {
                account.getLock().unlockRead(stamp);
            }
        }
    }

}
