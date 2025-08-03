package task.famous.advanced.task27.solution1;

import java.math.BigDecimal;
import java.util.UUID;

record InnerAccountMoneyTransferData(
        UUID id,
        BigDecimal amount
) {}
