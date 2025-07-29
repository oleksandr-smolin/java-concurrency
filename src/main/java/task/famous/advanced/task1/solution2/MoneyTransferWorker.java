package task.famous.advanced.task1.solution2;

class MoneyTransferWorker implements Runnable {
    
    private final MoneyTransferService moneyTransferService;
    private final AccountTransfer accountTransfer;

    MoneyTransferWorker(MoneyTransferService moneyTransferService, AccountTransfer accountTransfer) {
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
