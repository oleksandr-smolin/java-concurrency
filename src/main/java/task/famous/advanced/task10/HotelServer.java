package task.famous.advanced.task10;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

public class HotelServer {

  private static final int NUMBER_OF_THREADS_FOR_CLIENT_REQUESTS = 8;
  private static final int NUMBER_OF_BOOKING_PROCESSORS = 4;

  public static void main(String[] args) throws IOException {
    final var bookingRepository = BookingRepository.getInstance();
    final var queue = new LinkedBlockingDeque<Booking>();

    final var executorService = Executors.newFixedThreadPool(NUMBER_OF_BOOKING_PROCESSORS);

    IntStream.range(0, NUMBER_OF_BOOKING_PROCESSORS).mapToObj(i -> new Worker(bookingRepository, queue))
        .forEach(executorService::submit);

    final HttpHandler httpHandler = new BookingHttpHandler(bookingRepository,  queue);
    startServer(httpHandler);
  }

  public static void startServer(HttpHandler httpHandler) throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/booking", httpHandler);
    var executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS_FOR_CLIENT_REQUESTS);
    server.setExecutor(executor);
    server.start();
  }
}
