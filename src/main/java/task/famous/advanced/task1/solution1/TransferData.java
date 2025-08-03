package task.famous.advanced.task1.solution1;

import java.math.BigDecimal;

record TransferData(
    long userIdFrom,
    long userIdTo,
    BigDecimal amount
) {}
