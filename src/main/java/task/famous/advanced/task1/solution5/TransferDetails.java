package task.famous.advanced.task1.solution5;

import java.math.BigDecimal;
import java.util.UUID;

record TransferDetails (
        UUID senderAccountId,
        UUID receiverAccountId,
        BigDecimal amount
) {}
