package task.famous.advanced.task27.solution1;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.StampedLock;

class Account {

    private final UUID id;

    private BigDecimal balance;

    private final StampedLock stampedLock = new StampedLock();

    Account(UUID id, BigDecimal bigDecimal) {
        this.id = id;
        this.balance = bigDecimal;
    }

    BigDecimal deposit(BigDecimal amount) {
        BigDecimal currentBalance;
        long stamp = stampedLock.writeLock();
        balance = balance.add(amount);
        currentBalance = balance;
        stampedLock.unlockWrite(stamp);
        return currentBalance;
    }

    BigDecimal getBalance() {
        long stamp = stampedLock.tryOptimisticRead();
        if (stampedLock.validate(stamp)) {
            return balance;
        } else {
            long pesemisticStampReadLock = stampedLock.readLock();
            try {
                return balance;
            } finally {
                stampedLock.unlockRead(pesemisticStampReadLock);
            }
        }
    }

    public UUID getId() {
        return id;
    }
}
