package task.famous.advanced.task15.solution2;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class LoadBalancer {

    private final List<Server> serverList = IntStream.rangeClosed(1, 3).mapToObj(Server::new).toList();

    private final AtomicInteger currentPoint = new AtomicInteger(0);

    public Response route(Request request) {
        return serverList.get(getCurrentPoint()).processRequest(request);
    }

    private int getCurrentPoint() {
        return Math.abs(currentPoint.getAndIncrement() % 3);
    }
}
