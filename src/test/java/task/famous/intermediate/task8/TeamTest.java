package task.famous.intermediate.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TeamTest {

    @Test
    void shouldCompleteAllStagesOfWork() throws InterruptedException {
        int numberOfThread = 1;
        var stages = new ConcurrentLinkedDeque<String>();
        CyclicBarrier stageSynchronizer = new CyclicBarrier(numberOfThread, () -> {});

        List<Runnable> tasks = IntStream.range(0, numberOfThread)
                .mapToObj(i -> (Runnable) new Team(stages, stageSynchronizer)).toList();

        try (ExecutorService es = Executors.newFixedThreadPool(numberOfThread)) {
            tasks.forEach(es::submit);
        }

        Assertions.assertEquals(List.of("design", "development", "testing"), new ArrayList<>(stages));
    }

    @Test
    void stagesShouldSaveOrderWhenManyTeamsAreWorking() {
        int numberOfThread = 100;
        var stages = new ConcurrentLinkedDeque<String>();

        CyclicBarrier stageSynchronizer = new CyclicBarrier(numberOfThread, () -> {});

        List<Runnable> tasks = IntStream.range(0, numberOfThread)
                .mapToObj(i -> (Runnable) new Team(stages, stageSynchronizer)).toList();

        try (ExecutorService es = Executors.newFixedThreadPool(numberOfThread)) {
            tasks.forEach(es::submit);
        }

        var expectedList = Stream.of("design", "development", "testing")
                .flatMap(stage -> Stream.generate(() -> stage).limit(numberOfThread)).toList();

        Assertions.assertEquals(expectedList, new ArrayList<>(stages));
    }

}
