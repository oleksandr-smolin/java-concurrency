package task.famous.intermediate.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TeamTest {

    private static final String STAGE_PASSED_POSTFIX = "_stagePassed";

    @Test
    void shouldCompleteAllStagesOfWork() throws InterruptedException {
        int numberOfThread = 1;

        var stages = new ConcurrentLinkedDeque<String>();

        CyclicBarrier stageSynchronizer = new CyclicBarrier(numberOfThread,
                () -> stages.add(stages.peekLast() + STAGE_PASSED_POSTFIX));

        List<Thread> threadList = IntStream.range(0, numberOfThread)
                .mapToObj(i -> new Thread(new Team(stages, stageSynchronizer))).toList();

        threadList.forEach(Thread::start);
        for (var thread : threadList) thread.join();

        var expectedDeque = Stream.of("design", "development", "testing")
                .map(stage -> Stream.concat(
                        Stream.generate(() -> stage).limit(numberOfThread),
                        Stream.of(stage + STAGE_PASSED_POSTFIX)))
                .flatMap(Stream::distinct).collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

        Assertions.assertEquals(new ArrayList<>(expectedDeque), new ArrayList<>(stages));
    }

    @Test
    void stagesShouldSaveOrderWhenManyTeamsAreWorking() throws InterruptedException {
        int numberOfThread = 100;

        var stages = new ConcurrentLinkedDeque<String>();

        CyclicBarrier stageSynchronizer = new CyclicBarrier(numberOfThread,
                () -> stages.add(stages.peekLast() + STAGE_PASSED_POSTFIX));

        List<Thread> threadList = IntStream.range(0, numberOfThread)
                .mapToObj(i -> new Thread(new Team(stages, stageSynchronizer))).toList();

        threadList.forEach(Thread::start);
        for (var thread : threadList) thread.join();

        var expectedDeque = Stream.of("design", "development", "testing")
                .flatMap(stage -> Stream.concat(
                        Stream.generate(() -> stage).limit(numberOfThread),
                        Stream.of(stage + STAGE_PASSED_POSTFIX)))
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

        Assertions.assertEquals(new ArrayList<>(expectedDeque), new ArrayList<>(stages));
    }
}
