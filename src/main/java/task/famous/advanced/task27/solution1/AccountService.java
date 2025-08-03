package task.famous.advanced.task27.solution1;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class AccountService {

    private final Map<UUID, Account> accountMap;

    AccountService() {
        accountMap = new ConcurrentHashMap<>();
        UUID firstAccountId = UUID.randomUUID();
        accountMap.put(firstAccountId, new Account(firstAccountId, new BigDecimal("10000000")));
    }

    public BigDecimal deposit(InnerAccountMoneyTransferData transferData) {
        Account account = accountMap.get(transferData.id());

        return account.deposit(transferData.amount());
    }


    Collection<Account> getAccounts() {
        return accountMap.values();
    }
}
