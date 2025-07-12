package task.famous.advanced.task1.solution2;

public record AccountTransfer(
        Account source,
        Account target,
        int amount
) {}
