package task.famous.advanced.task14;

import java.util.concurrent.atomic.AtomicInteger;

public class Route {


  private final AtomicInteger availableSeats = new AtomicInteger(1000);
  private final int trainNum;

  public Route(int trainNum) {
    this.trainNum = trainNum;
  }

  public void bookSeats(int numberOfSeats) throws BookingException {
    int freeSeats = availableSeats.updateAndGet(currentNum -> currentNum - numberOfSeats);
    if (freeSeats < 0) {
      availableSeats.addAndGet(numberOfSeats);
      throw new BookingException("Trying to book more seats then available");
    }
  }

  public int getNumOfFreeSeats() {
    return availableSeats.get();
  }

  @Override
  public String toString() {
    return "Route{" +
        "availableSeats=" + availableSeats +
        ", trainNum=" + trainNum +
        '}';
  }
}
