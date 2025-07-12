package task.famous.advanced.task1.solution2;

public class MoneyTransferWorker implements Runnable {
    
    private final MoneyTransferService moneyTransferService;
    private final AccountTransfer accountTransfer;

    public MoneyTransferWorker(MoneyTransferService moneyTransferService, AccountTransfer accountTransfer) {
        this.moneyTransferService = moneyTransferService;
        this.accountTransfer = accountTransfer;
    }

    @Override
    public void run() {
        try {
            moneyTransferService.transferMoney(accountTransfer);
        } catch (NotEnoughMoney e) {
            throw new RuntimeException(e);
        }
    }
}
