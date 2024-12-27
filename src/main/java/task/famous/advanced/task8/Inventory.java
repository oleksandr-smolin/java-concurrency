package task.famous.advanced.task8;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Inventory {

  private final Random random = new Random();
  private final Map<Integer, Order> store = Collections.synchronizedMap(new HashMap<>());

  private static Inventory INVENTORY = new Inventory();

  private Inventory() {}

  public static Inventory getInstance() {
    return INVENTORY;
  }

  public int addItem(Order order) {
    int idOfNewOrder = random.nextInt(Integer.MAX_VALUE);
    store.put(idOfNewOrder, order);
    return idOfNewOrder;
  }

  public Map<Integer, Order> getOrders() {
    return Map.copyOf(store);
  }

}
