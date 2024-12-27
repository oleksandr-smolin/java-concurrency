package task.famous.advanced.task10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

public class WorkerTest {

  @BeforeEach
  void setup() {
    BookingRepository.getInstance().clear();
  }

  @Test
  void shouldProcessSingleBookingSuccessfully() throws InterruptedException {
    final var repository = BookingRepository.getInstance();
    final var queue = new LinkedBlockingDeque<Booking>();

    Thread workerThread = new Thread(new Worker(repository, queue));
    workerThread.start();

    Booking booking = new Booking(1, "John Doe", 101, LocalDate.now().plusDays(1));
    queue.offer(booking);

    workerThread.interrupt();
    workerThread.join();

    Assertions.assertTrue(repository.findAll().contains(booking));
  }

  @Test
  void shouldAllowOnlyOneWorkerToProcessBooking() throws InterruptedException {
    final var repository = BookingRepository.getInstance();
    final var queue = new LinkedBlockingDeque<Booking>();

    List<Thread> threadList = List.of(
        new Thread(new Worker(repository, queue)),
        new Thread(new Worker(repository, queue)),
        new Thread(new Worker(repository, queue)),
        new Thread(new Worker(repository, queue)));

    threadList.forEach(Thread::start);

    Booking booking = new Booking(1, "John Doe", 101, LocalDate.now().plusDays(1));
    queue.offer(booking);

    threadList.forEach(Thread::interrupt);
    for (var thread : threadList) thread.join();

    Assertions.assertTrue(repository.findAll().contains(booking));
    Assertions.assertEquals(1, repository.findAll().size());
  }

  @Test
  void shouldProcessManyBookingSuccessfully() throws InterruptedException {
    final var repository = BookingRepository.getInstance();
    final var queue = new LinkedBlockingDeque<Booking>();

    List<Thread> threadList = List.of(
        new Thread(new Worker(repository, queue)),
        new Thread(new Worker(repository, queue)),
        new Thread(new Worker(repository, queue)),
        new Thread(new Worker(repository, queue)));

    threadList.forEach(Thread::start);

    List<Booking> bookingList = generateBookings(10000);
    bookingList.forEach(queue::offer);

    threadList.forEach(Thread::interrupt);
    for (var thread : threadList) thread.join();

    Assertions.assertEquals(0, queue.size());
    Assertions.assertTrue(bookingList.containsAll(repository.findAll()));
  }

  private List<Booking> generateBookings(int numberOfBooking) {
    return IntStream.range(0, numberOfBooking)
        .mapToObj(i -> new Booking(i, "User" + i, 100 + i, LocalDate.now().plusDays(i+1)))
        .toList();
  }
}
