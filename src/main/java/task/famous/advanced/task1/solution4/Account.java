package task.famous.advanced.task1.solution4;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

class Account {

    private final ReentrantLock lock = new ReentrantLock();

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

    public ReentrantLock getLock() {
        return lock;
    }
}
