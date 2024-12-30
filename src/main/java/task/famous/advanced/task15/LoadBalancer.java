package task.famous.advanced.task15;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer implements HttpHandler {

  private final List<Server> servers;
  private final AtomicInteger counter = new AtomicInteger();

  public LoadBalancer(List<Server> servers) {
    this.servers = servers;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    int requestNum = counter.getAndIncrement();

    Request request = new Request(requestNum, exchange.getRequestURI().getQuery());
    servers.get((requestNum % servers.size())).sendRequest(request);

    try {
      String response = request.getResponse();

      exchange.sendResponseHeaders(200, response.getBytes().length);
      exchange.getResponseBody().write(response.getBytes());
    } catch (InterruptedException e) {
      exchange.sendResponseHeaders(500, 0);
      exchange.getResponseBody().write("Error processing request.".getBytes());
    } finally {
      exchange.close();
    }
  }
}
