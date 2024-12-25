package task.famous.advanced.task10;

import task.famous.advanced.task10.exception.ParsingException;

import java.time.LocalDate;

public record Booking(
    int id,
    String name,
    int room,
    LocalDate date

) {

  public Booking {
    if (room < 1) throw new ParsingException("room can't be less then 1");
    if (name.matches(".*[1-9].*")) throw new ParsingException("Name can't include Numbers");
    if (room < 0) throw new ParsingException("room can't be less then 0");
    if (date.isBefore(LocalDate.now().plusDays(1))) throw new ParsingException("Today's or past days can't be chosen");
  }
}
