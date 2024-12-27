package task.famous.advanced.task10;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import task.famous.advanced.task10.exception.HighLoadException;
import task.famous.advanced.task10.exception.ParsingException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BookingHttpHandler implements HttpHandler {
  private static final Random RANDOM = new Random();
  public static final String SUCCESS_ANSWER = "Thank you for you booking, your booking will be processed soon and you will see accepted booking in your account";
  private final BookingRepository bookingRepository;
  private final BlockingQueue<Booking> queue;

  public BookingHttpHandler(BookingRepository bookingRepository, BlockingQueue<Booking> queue) {
    this.bookingRepository = bookingRepository;
    this.queue = queue;
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    String response = switch (exchange.getRequestMethod()) {
      case "GET" -> handleGetRequest(exchange);
      case "POST" -> handlePostRequest(exchange);
      default -> "";
    };

    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }

  private String handleGetRequest(HttpExchange exchange) throws IOException {
    Map<String,String> params = parseQueryParamsToMap(exchange.getRequestURI().getQuery());

    String response = Optional.ofNullable(params.get("id")).map(Integer::parseInt)
        .map(bookingRepository::findBooking).map(String::valueOf)
        .orElseGet(() -> Arrays.toString(bookingRepository.findAll().toArray()));

    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
    return response;
  }

  private String handlePostRequest(HttpExchange exchange) throws IOException {
    Booking booking;
    try {
       booking = parseQueryParams(exchange.getRequestURI().getQuery());
    } catch (ParsingException ex) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, ex.getMessage().length());
      return ex.getMessage();
    }

    try {
      pushTaskToProcessing(booking);
    } catch (HighLoadException ex) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, ex.getMessage().length());
      return ex.getMessage();
    }

    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, SUCCESS_ANSWER.length());
    return SUCCESS_ANSWER;
  }

  private void pushTaskToProcessing(Booking booking) throws HighLoadException {
    try {
      queue.offer(booking, 2, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new HighLoadException("Sorry because of high load we can't process your request, please try again later");
    }
  }

  private Booking parseQueryParams(String query) throws ParsingException {
    Map<String, String> params = parseQueryParamsToMap(query);
    if (params == null) return null;

    return new Booking(
        RANDOM.nextInt(Integer.MAX_VALUE -1),
        params.get("name"),
        Integer.parseInt(params.get("room")),
        LocalDate.parse(params.get("date"))
    );
  }

  private static Map<String, String> parseQueryParamsToMap(String query) {
    Optional<String[]> extractedPairs = Optional.ofNullable(query)
        .filter(__ -> !__.isEmpty()).map(__ -> __.split("&")).filter(pairs -> pairs.length != 0);

    if (extractedPairs.isEmpty()) return Map.of();

    return Arrays.stream(extractedPairs.get())
        .map(pair -> pair.split("="))
        .filter(keyValue -> !keyValue[0].isEmpty() && !keyValue[1].isEmpty())
        .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
  }
}
