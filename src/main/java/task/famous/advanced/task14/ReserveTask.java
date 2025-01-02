package task.famous.advanced.task14;

import java.util.concurrent.ThreadLocalRandom;

public class ReserveTask implements Runnable {

  private final ReservationStore reservationStore;

  public ReserveTask(ReservationStore reservationStore) {
    this.reservationStore = reservationStore;
  }

  @Override
  public void run() {
    int trainNum = ThreadLocalRandom.current().nextInt(1, 5);
    int numberOfSeats = ThreadLocalRandom.current().nextInt(1, 2);

    try {
      reservationStore.reserveSeats(trainNum, numberOfSeats);
    } catch (BookingException e) {
      System.err.println(Thread.currentThread().getName() +
          " can't book seats (%d) in train with number: %d".formatted(numberOfSeats, trainNum));
    }
  }
}
