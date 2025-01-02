package task.famous.advanced.taskt14;

import org.junit.jupiter.api.Test;
import task.famous.advanced.task14.ReservationStore;
import task.famous.advanced.task14.ReserveTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationSystemTest {


  @Test
  void testReservation() {
    ReservationStore reservationStore = ReservationStore.getInstance();

    try (ExecutorService executorService = Executors.newFixedThreadPool(12)) {
      IntStream.range(0, 1000).forEach(i -> executorService.submit(new ReserveTask(reservationStore)));
    }

    assertTrue(reservationStore.getStore().stream().anyMatch(entry -> entry.getValue().getNumOfFreeSeats() < 100));
  }
}
