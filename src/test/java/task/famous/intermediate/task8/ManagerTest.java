package task.famous.intermediate.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static task.famous.intermediate.task8.Manager.STAGE_PASSED_POSTFIX;

public class ManagerTest {

    @Test
    void shouldProduceStagesWithOneTeam() throws InterruptedException {
        int teamNumber = 1;

        Manager manager = new Manager();
        List<String> stages = manager.startWorkProcess(teamNumber);

        var exepectedList = Stream.of("design", "development", "testing")
                .map(stage -> Stream.concat(
                        Stream.generate(() -> stage).limit(teamNumber),
                        Stream.of(stage + STAGE_PASSED_POSTFIX)))
                .flatMap(Stream::distinct).toList();

        Assertions.assertEquals(exepectedList, new ArrayList<>(stages));
    }

    @Test
    void shouldProduceStagesWithManyTeams() throws InterruptedException {
        int teamNumber = 10;

        Manager manager = new Manager();
        List<String> stages = manager.startWorkProcess(teamNumber);

        var exepectedList = Stream.of("design", "development", "testing")
                .flatMap(stage -> Stream.concat(
                        Stream.generate(() -> stage).limit(teamNumber),
                        Stream.of(stage + STAGE_PASSED_POSTFIX)))
                .toList();

        Assertions.assertEquals(exepectedList, new ArrayList<>(stages));
    }
}
