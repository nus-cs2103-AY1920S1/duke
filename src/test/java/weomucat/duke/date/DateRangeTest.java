package weomucat.duke.date;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import weomucat.duke.date.Date;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;

public class DateRangeTest {

  @Test
  public void fromDateShouldNotBeAfterToDate() {
    Date from = new Date(LocalDateTime.now().plusMinutes(1));
    Date to = new Date(LocalDateTime.now());
    assertThrows(InvalidParameterException.class, () -> new DateRange(from, to),
        formatMessage(from, to));
  }

  private String formatMessage(Date from, Date to) {
    return String.format("Input: new DateRange('%s','%s')", from, to);
  }
}
