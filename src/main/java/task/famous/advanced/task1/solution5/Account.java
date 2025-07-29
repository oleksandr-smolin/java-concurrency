package task.famous.advanced.task1.solution5;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

class Account {

    private final StampedLock lock = new StampedLock();

    private UUID id;
    private BigDecimal balance;

    Account(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    UUID id() {
        return id;
    }

    BigDecimal balance() {
        return balance;
    }

    void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public StampedLock getLock() {
        return lock;
    }
}
