package task.famous.intermediate.task8;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class Manager {

    static final String STAGE_PASSED_POSTFIX = "_stagePassed";

    public List<String> startWorkProcess(int teamNumber) {
        var stages = new ConcurrentLinkedDeque<String>();

        CyclicBarrier stageSynchronizer = new CyclicBarrier(teamNumber,
                () -> stages.add(stages.peekLast() + STAGE_PASSED_POSTFIX));

        List<Runnable> tasks = IntStream.range(0, teamNumber)
                .mapToObj(i -> (Runnable) new Team(stages, stageSynchronizer)).toList();

        try (ExecutorService es = Executors.newFixedThreadPool(teamNumber)) {
            tasks.forEach(es::submit);
        }

        return List.copyOf(stages);
    }
}
