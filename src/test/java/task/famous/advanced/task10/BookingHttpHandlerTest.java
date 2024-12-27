package task.famous.advanced.task10;

import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.time.LocalDate;
import java.util.concurrent.LinkedBlockingDeque;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static task.famous.advanced.task10.BookingHttpHandler.SUCCESS_ANSWER;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingHttpHandlerTest {

  @Mock
  private HttpExchange exchange;

  @Test
  void testThatObjectReturnsResponseImmediately() throws IOException {
    var outputStream = new ByteArrayOutputStream();
    when(exchange.getResponseBody()).thenReturn(outputStream);
    when(exchange.getRequestMethod()).thenReturn("POST");

    String name = "JohnWeek";
    int room = 101;
    LocalDate date = LocalDate.now().plusDays(1);

    when(exchange.getRequestURI()).thenReturn(URI.create(
        "http://localhost:8000/booking?name=%s&room=%d&date=%s".formatted(name, room, date)));

    final var queue = new LinkedBlockingDeque<Booking>();
    final var repository = BookingRepository.getInstance();

    final var bookingHttpHandler = new BookingHttpHandler(repository, queue);

    bookingHttpHandler.handle(exchange);

    Assertions.assertEquals(1, queue.size());
    final var booking = queue.poll();
    Assertions.assertTrue(booking.id() > 0);
    Assertions.assertEquals(name, booking.name());
    Assertions.assertEquals(room, booking.room());
    Assertions.assertEquals(date, booking.date());

    verify(exchange).sendResponseHeaders(HttpURLConnection.HTTP_OK, SUCCESS_ANSWER.length());

    Assertions.assertEquals(SUCCESS_ANSWER, outputStream.toString());
  }
}
