package task.famous.advanced.task10;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BookingRepository {

  private static final Map<Integer, Booking> BOOKING_MAP = Collections.synchronizedMap(new HashMap<>());
  private static final BookingRepository INSTANCE = new BookingRepository();
  public static BookingRepository getInstance(){
    return INSTANCE;
  }

  private BookingRepository(){
    final var random = new Random();

    List<Booking> alreadyRegisteredBookingList = List.of(
//        new Booking(random.nextInt(Integer.MAX_VALUE - 1), "Jack", 201, LocalDate.now().plusDays(33)),
//        new Booking(random.nextInt(Integer.MAX_VALUE - 1), "Sara", 123, LocalDate.now().plusDays(22)),
//        new Booking(random.nextInt(Integer.MAX_VALUE - 1), "Johny", 65, LocalDate.now().plusDays(7))
    );

    alreadyRegisteredBookingList.forEach(booking -> BOOKING_MAP.put(booking.id(), booking));
  }

  public Booking findBooking(int id) {
    return BOOKING_MAP.get(id);
  }

  public Collection<Booking> findAll() {
    return BOOKING_MAP.values();
  }

  public void saveBooking(Booking booking) {
    BOOKING_MAP.put(booking.id(), booking);
  }

  public void clear() {
    BOOKING_MAP.clear();
  }
}
