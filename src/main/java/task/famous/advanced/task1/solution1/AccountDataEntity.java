package task.famous.advanced.task1.solution1;

import java.math.BigDecimal;

public class AccountDataEntity {

  private Long userId;
  private BigDecimal balance;

  public AccountDataEntity(Long userId, BigDecimal balance) {
    this.userId = userId;
    this.balance = balance;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "AccountDataEntity{" +
        "userId=" + userId +
        ", balance=" + balance +
        '}';
  }
}
