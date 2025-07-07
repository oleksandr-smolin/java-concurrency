package task.famous.basic.task7;

import java.util.List;

class Worker implements Runnable {

    public Worker(Printer printer, List<Integer> intList) {
        this.printer = printer;
        this.intList = intList;
    }

    private final Printer printer;
    private final List<Integer> intList;


    @Override
    public void run() {
        intList.forEach(printer::print);
    }
}
