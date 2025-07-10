package task.famous.intermediate.task8;

import java.util.Deque;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Team implements Runnable{

    public Team(Deque<String> stages, CyclicBarrier stageSynchronizer) {
        this.completedWorkStages = stages;
        this.stageSynchronizer = stageSynchronizer;
    }

    private final Deque<String> completedWorkStages;
    private final CyclicBarrier stageSynchronizer;

    @Override
    public void run() {
        completedWorkStages.add("design");
        synchronizeStage();
        completedWorkStages.add("development");
        synchronizeStage();
        completedWorkStages.add("testing");
        synchronizeStage();
    }

    private void synchronizeStage() {
        try {
            stageSynchronizer.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
