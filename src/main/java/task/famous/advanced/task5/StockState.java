package task.famous.advanced.task5;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public record StockState(
    List<Stock> stocks,
    LocalDateTime dateTime
) {

  @Override
  public String toString() {
    return "StockState{" +
        "stocks=" + Arrays.toString(stocks.toArray()) +
        ", dateTime=" + dateTime +
        '}';
  }
}
