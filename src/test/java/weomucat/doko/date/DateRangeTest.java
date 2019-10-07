package weomucat.doko.date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.doko.exception.DokoRuntimeException;
import weomucat.doko.random.RandomDate;

class DateRangeTest {

  private static final int RANDOM_TESTS = 5;

  @Test
  void fromDateShouldNotBeAfterToDate() {
    for (int i = 0; i < RANDOM_TESTS; i++) {
      Date from = RandomDate.generate();
      Date to = RandomDate.generate();
      if (from.compareTo(to) < 0) {
        assertDoesNotThrow(() -> new DateRange(from, to),
            formatMessage(from, to));
      } else {
        assertThrows(DokoRuntimeException.class, () -> new DateRange(from, to),
            formatMessage(from, to));
      }
    }
  }

  private String formatMessage(Date from, Date to) {
    return String.format("Input: new DateRange('%s','%s')", from, to);
  }
}
