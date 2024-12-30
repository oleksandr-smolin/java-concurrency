package task.famous.advanced.task1;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataStore {

  private static final Map<Long, AccountDataEntity> STORE = Collections.synchronizedMap(new HashMap<>());

  private static final DataStore INSTANCE = new DataStore();

  private DataStore() {
    STORE.put(1L, new AccountDataEntity(1L, new BigDecimal("677.90")));
    STORE.put(2L, new AccountDataEntity(2L, new BigDecimal("400.12")));
    STORE.put(3L, new AccountDataEntity(3L, new BigDecimal("2887.33")));
  }

  public static DataStore getInstance() {
    return INSTANCE;
  }

  public synchronized boolean transfer(TransferData transferData) {
    final var fromAccount = STORE.get(transferData.userIdFrom());

    if (fromAccount.getBalance().compareTo(transferData.amount()) < 0) {
      return false;
    }

    final var toAccount = STORE.get(transferData.userIdTo());

    toAccount.setBalance(toAccount.getBalance().add(transferData.amount()));
    fromAccount.setBalance(fromAccount.getBalance().subtract(transferData.amount()));

    return true;
  }

  public synchronized Map<Long, AccountDataEntity> getAllAccounts() {
    return new HashMap<>(STORE);
  }
}
