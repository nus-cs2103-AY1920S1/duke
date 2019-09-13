package weomucat.duke.date;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import weomucat.duke.exception.InvalidParameterException;

public class DateRangeTest {

  @Test
  public void fromDateShouldNotBeAfterToDate() {
    Date from = Date.now().plus(Duration.ofMinutes(1));
    Date to = Date.now();
    assertThrows(InvalidParameterException.class, () -> DateRange.create(from, to),
        formatMessage(from, to));
  }

  private String formatMessage(Date from, Date to) {
    return String.format("Input: new DateRange('%s','%s')", from, to);
  }
}
