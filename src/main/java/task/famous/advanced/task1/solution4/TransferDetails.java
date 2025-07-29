package task.famous.advanced.task1.solution4;

import java.math.BigDecimal;
import java.util.UUID;

record TransferDetails (
        UUID senderAccountId,
        UUID receiverAccountId,
        BigDecimal amount
) {}
