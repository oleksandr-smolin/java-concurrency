package task.famous.basic.task5;

import java.util.List;
import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {

    public Worker(List<Integer> intList) {
        this.intList = intList;
    }

    private final List<Integer> intList;

    @Override
    public Integer call() throws Exception {
        System.out.println("I want to sum numbers " + Thread.currentThread().getName());
        return intList.stream().mapToInt(Integer::intValue).sum();
    }
}
