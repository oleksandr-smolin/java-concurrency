package task.famous.advanced.task8;

import java.math.BigDecimal;

public record Order(String name, int quantity, BigDecimal price) {

  public Order {
    if (name == null || name.isEmpty()) throw new OrderObjectCreationException("Item name can't be empty");
    if (quantity < 1) throw new OrderObjectCreationException("Quantity can't be less then 1");
    if (price.compareTo(BigDecimal.ONE) < 0) throw new OrderObjectCreationException("Price can't be less then 1");
  }
}
