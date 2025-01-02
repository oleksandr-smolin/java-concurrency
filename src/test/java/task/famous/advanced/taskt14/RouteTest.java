package task.famous.advanced.taskt14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.famous.advanced.task14.BookingException;
import task.famous.advanced.task14.Route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.fail;

public class RouteTest {


  @Test
  void bookingSeats_ShouldBe_ThreadSave_Action() {
    Route route = new Route(123);

    try(ExecutorService executorService = Executors.newFixedThreadPool(12)) {
      IntStream.range(0, 1000).mapToObj(i -> new Thread(() -> {
        try {
          route.bookSeats(1);
        } catch (BookingException e) {
          fail("BookingException was thrown: " + e.getMessage());
        }
      })).forEach(executorService::submit);
    }

    Assertions.assertEquals(0, route.getNumOfFreeSeats());
  }
}
