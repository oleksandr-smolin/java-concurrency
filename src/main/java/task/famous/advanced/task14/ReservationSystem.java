package task.famous.advanced.task14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ReservationSystem {

  public void startReservationSystem() {
    ReservationStore reservationStore = ReservationStore.getInstance();

    try (ExecutorService executorService = Executors.newFixedThreadPool(12)) {
      IntStream.range(0, 5).forEach(i -> executorService.submit(new ReserveTask(reservationStore)));
    }

    reservationStore.getStore().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
  }
}
