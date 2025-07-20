package task.famous.intermediate.task37;

import java.util.UUID;

record Message (
        UUID chatId,
        User from,
        User to,
        String messageBody
) {}
