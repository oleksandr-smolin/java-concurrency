package task.famous.advanced.task10;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

  private final BookingRepository bookingRepository;
  private final BlockingQueue<Booking> queue;

  public Worker(BookingRepository bookingRepository, BlockingQueue<Booking> queue) {
    this.bookingRepository = bookingRepository;
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      Booking booking;
      try {
        booking = queue.take();
        System.out.println(Thread.currentThread().getName() + "I'm processing Order: " + booking);
        Thread.sleep((int)(Math.random() * 5000) + 1500);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      bookingRepository.saveBooking(booking);
      System.out.println(Thread.currentThread().getName() + "I'm finished processing booking: " + booking);
    }
  }
}
