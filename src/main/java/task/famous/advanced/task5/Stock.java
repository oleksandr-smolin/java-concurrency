package task.famous.advanced.task5;

import java.math.BigDecimal;

public record Stock(
    long id,
    String name,
    BigDecimal price
) {}
