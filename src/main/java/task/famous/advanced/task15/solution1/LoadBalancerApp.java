package task.famous.advanced.task15.solution1;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class LoadBalancerApp {

  public static void main(String[] args) throws IOException {
    startApp();
  }

  public static void startApp() throws IOException {
    List<Server> serverList = IntStream.range(0, 5)
        .mapToObj(i -> new Server("Server-" + i))
        .toList();

    ExecutorService serverExecutor = Executors.newFixedThreadPool(serverList.size());
    serverList.forEach(serverExecutor::submit);

    HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
    Executor virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
    httpServer.createContext("/order", new LoadBalancer(serverList));
    httpServer.setExecutor(virtualThreadExecutor);
    httpServer.start();
  }

}
