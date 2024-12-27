package task.famous.advanced.task8;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.stream.Collectors;

public class OrderHttpHandler implements HttpHandler {

  private static final Inventory inventory = Inventory.getInstance();

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String response;
    if (exchange.getRequestMethod().equals("POST")) {
      try {
        response = handleOrderCreation(exchange);
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
      } catch (Exception ex) {
        response = ex.getMessage();
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, response.length());
      }
    } else {
      response = "Request Method isn't available";
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, response.length());
    }

    var outputStream =exchange.getResponseBody();
    outputStream.write(response.getBytes());
    outputStream.close();
  }

  private String handleOrderCreation(HttpExchange exchange) {
    Order order = parseToOrderObject(exchange);

    int idOfOrder = inventory.addItem(order);

    return "Order successfully created, orderId: " + idOfOrder;
  }

  private Order parseToOrderObject(HttpExchange exchange) {
    var params = Arrays.stream(exchange.getRequestURI().getQuery().split("&"))
        .map(keyValuePair -> keyValuePair.split("="))
        .collect(Collectors.toMap(keyValueArray -> keyValueArray[0], keyValueArray -> keyValueArray[1]));

    return new Order(params.get("name"), Integer.parseInt(params.get("quantity")), new BigDecimal(params.get("price")));
  }
}
