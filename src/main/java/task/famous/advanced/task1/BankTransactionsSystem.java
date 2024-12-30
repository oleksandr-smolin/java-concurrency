package task.famous.advanced.task1;

import java.math.BigDecimal;

public class BankTransactionsSystem {

  public static void main(String[] args) {

    TransferService transferService = new TransferService(DataStore.getInstance());

    TransferData transferData = new TransferData(1, 2, new BigDecimal("10.10"));

    System.out.println(transferService.transferToAnotherAccount(transferData));
  }
}
