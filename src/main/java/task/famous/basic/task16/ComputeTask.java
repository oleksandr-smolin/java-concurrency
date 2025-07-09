package task.famous.basic.task16;

import java.util.concurrent.RecursiveTask;

class ComputeTask extends RecursiveTask<Long> {
    private final long startNumber;
    private final long finishNumber;

    ComputeTask(long startNumber, long finishNumber) {
        this.startNumber = startNumber;
        this.finishNumber = finishNumber;
    }

    @Override
    protected Long compute() {
        if (startNumber == finishNumber) {
            return startNumber;
        } else if (startNumber - finishNumber == 1l) {
            return startNumber * finishNumber;
        } else {
            long midNum = (startNumber + finishNumber) / 2;

            var left = new ComputeTask(startNumber, midNum);
            var right = new ComputeTask(midNum-1, finishNumber);
            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();

            return leftResult * rightResult;
        }
    }
}
