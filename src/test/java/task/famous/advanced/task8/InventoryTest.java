package task.famous.advanced.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class InventoryTest {

  @Test
  void testOrdersSaveSuccessfully() {
    final var inventory = Inventory.getInstance();
    final int numberOfTasks = 1000;

    try (final var es = Executors.newFixedThreadPool(10)) {
      IntStream.rangeClosed(0, numberOfTasks).forEach(i -> es.submit(() -> {
        Order order = new Order("Lamp1", i, new BigDecimal("50.2"));
        inventory.addItem(order);
      }));
    }

    Assertions.assertEquals(numberOfTasks, inventory.getOrders().size());
  }
}
