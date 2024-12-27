package task.famous.advanced.task10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelServerTest {


  @BeforeEach
  void setup() throws InterruptedException {
    Thread serverThread = new Thread(() -> {
      try {
        HotelServer.main(new String[]{});
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
    serverThread.start();

    Thread.sleep(2000);

    if(serverThread.isInterrupted()) throw new InterruptedException();
  }

  @Test
  void testServerHandlesRequestsAndProcessesBookings() throws Exception {



    URL url = new URL("http://localhost:8000/booking" + "?name=JohnDoe&room=101&date=" + LocalDate.now().plusDays(1));
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);

    int responseCode = connection.getResponseCode();
    assertEquals(200, responseCode);

    // Allow some time for processing
    Thread.sleep(1000);

    // Verify the booking is processed
    var repository = BookingRepository.getInstance();
    assertEquals(1, repository.findAll().size());
  }
}
