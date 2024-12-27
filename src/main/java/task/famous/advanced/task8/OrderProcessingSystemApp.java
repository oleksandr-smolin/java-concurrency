package task.famous.advanced.task8;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class OrderProcessingSystemApp {

  public static void main(String[] args) throws IOException {
    startServer();
  }

  private static void startServer() throws IOException {
    HttpServer httpServer = HttpServer.create();

    httpServer.bind(new InetSocketAddress(8000), 0);
    httpServer.createContext("/order", new OrderHttpHandler());
    httpServer.start();
  }
}
