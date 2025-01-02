package task.famous.advanced.task14;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReservationStore {

  private static final ReservationStore RESERVATION_STORE = new ReservationStore();

  private final Map<Integer, Route> routesMap = new HashMap<>();

  private ReservationStore() {
    routesMap.put(1, new Route(1));
    routesMap.put(2, new Route(2));
    routesMap.put(3, new Route(3));
    routesMap.put(4, new Route(4));
    routesMap.put(5, new Route(5));
  }

  public static ReservationStore getInstance() {
    return RESERVATION_STORE;
  }

  public void reserveSeats(int routeNum, int numberOfSeats) throws BookingException {
    routesMap.get(routeNum).bookSeats(numberOfSeats);
  }

  public Set<Map.Entry<Integer, Route>> getStore() {
    return routesMap.entrySet();
  }
}
