package task.famous.advanced.task1;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataStoreTest {

  @Test
  void testTransfer() {

    final var dataStore = DataStore.getInstance();

    TransferData transferData = new TransferData(1, 2, new BigDecimal("33.33"));

    boolean resultOfTransaction = dataStore.transfer(transferData);

    assertTrue(resultOfTransaction);
  }

  @Test
  void testTransferParallel() {

    final var dataStore = DataStore.getInstance();

    final var transfersList = List.of(
        new TransferData(1, 2, new BigDecimal("33.33")),
        new TransferData(2, 1, new BigDecimal("6.22")),
        new TransferData(3, 1, new BigDecimal("10.22")),
        new TransferData(2, 1, new BigDecimal("4.22")),
        new TransferData(2, 1, new BigDecimal("3.22"))
    );

    try (ExecutorService es = Executors.newFixedThreadPool(5)) {
      transfersList.stream().forEach(transferData -> es.submit(() -> dataStore.transfer(transferData)));
    }


    Map<Long, AccountDataEntity> accountDataEntityMap = dataStore.getAllAccounts();


    assertEquals(new BigDecimal("668.45"), accountDataEntityMap.get(1L).getBalance());
    assertEquals(new BigDecimal("419.79"), accountDataEntityMap.get(2L).getBalance());
    assertEquals(new BigDecimal("2877.11"), accountDataEntityMap.get(3L).getBalance());

  }

  @Test
  void testHighConcurrency() {
    var dataStore = DataStore.getInstance();

    int iterationNum = 100;
    int sumPerTransfer = 5;

    var accountStates = dataStore.getAllAccounts();
    final var account1InitSum = accountStates.get(1L).getBalance();
    final var account2InitSum = accountStates.get(2L).getBalance();

    try (ExecutorService es = Executors.newFixedThreadPool(10)) {
      for (int i = 0; i < iterationNum; i++) {
        es.submit(() -> dataStore.transfer(new TransferData(1, 2, new BigDecimal(sumPerTransfer))));
      }
    }

    BigDecimal transferTotal = new BigDecimal(iterationNum * sumPerTransfer);
    assertEquals(account1InitSum, accountStates.get(1L).getBalance().add(transferTotal));
    assertEquals(account2InitSum, accountStates.get(2L).getBalance().subtract(transferTotal));
  }

}
