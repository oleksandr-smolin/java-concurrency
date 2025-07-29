package task.famous.advanced.task1.solution3;

import java.math.BigDecimal;
import java.util.UUID;

class Account {

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
}
