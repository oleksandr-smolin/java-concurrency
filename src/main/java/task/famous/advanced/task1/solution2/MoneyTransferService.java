package task.famous.advanced.task1.solution2;

import java.util.concurrent.ThreadLocalRandom;

class MoneyTransferService {

    void transferMoney(AccountTransfer accountTransfer) throws NotEnoughMoney {
        ThreadLocalRandom.current().ints(500_000).forEach(__ -> {}); // add some work for thread, don't make its life to easy :)
        validateSum(accountTransfer);
        accountTransfer.source().creditBalance(accountTransfer.amount());
        accountTransfer.target().debitBalance(accountTransfer.amount());
    }

    private void validateSum(AccountTransfer accountTransfer) throws NotEnoughMoney {
        int accountABalance = accountTransfer.source().getBalance();
        int updatedAccountABalance = accountABalance - accountTransfer.amount();

        if (updatedAccountABalance < 0) throw new NotEnoughMoney();
    }
}
