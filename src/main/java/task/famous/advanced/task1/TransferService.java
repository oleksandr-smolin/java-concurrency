package task.famous.advanced.task1;

public class TransferService {

  private final DataStore dataStore;

  public TransferService(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  public boolean transferToAnotherAccount(TransferData transferData) {
    return dataStore.transfer(transferData);
  }
}
