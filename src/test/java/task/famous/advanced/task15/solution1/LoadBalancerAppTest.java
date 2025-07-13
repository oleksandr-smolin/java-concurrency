package task.famous.advanced.task15.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class LoadBalancerAppTest {

  @Test
  void testRequestProcessing() throws IOException {

    LoadBalancerApp.startApp();

    URL url = new URL("http://localhost:8000/order?someParam1=true&someParam2=33");

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoOutput(true);

    int responseCode = connection.getResponseCode();
    Assertions.assertEquals(200, responseCode);

    String response = new BufferedReader(new InputStreamReader(connection.getInputStream()))
        .lines()
        .reduce("", (accumulator, line) -> accumulator + line);

    Assertions.assertEquals("Request Processed by Server-0. request id: 1 params: someParam1=true&someParam2=33", response);
  }

  @Test
  void testManyRequestProcessing() throws IOException {
    LoadBalancerApp.startApp();

    try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
      IntStream.range(0, 1000).forEach(i -> executorService.submit(() -> {
        try {
          assertRequestRoutedOnCorrespondingServer(i);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }));
    }

    System.out.println("finished");
  }

  private void assertRequestRoutedOnCorrespondingServer(int i) throws IOException {
    URL url = new URL("http://localhost:8000/order?someParam1=%d&someParam2=33".formatted(i));

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoOutput(true);

    int responseCode = connection.getResponseCode();
    Assertions.assertEquals(200, responseCode);

    String response = new BufferedReader(new InputStreamReader(connection.getInputStream()))
        .lines()
        .reduce("", (accumulator, line) -> accumulator + line);

    int serverNum = i % (4+1);

    System.out.println("iNumber:" + i);
    Assertions.assertEquals(
        "Request Processed by Server-%d. request id: %d params: someParam1=%d&someParam2=33"
            .formatted(serverNum, i, i), response);

  }
}
