package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static weomucat.duke.date.Date.DATE_PARSE_PATTERN;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;

public class EventTaskTest {

  @Test
  public void descriptionShouldNotBeEmptyString() {
    // Format current datetime to a pattern which the command can parse.
    String at = DateRange.format(LocalDateTime.now().minusMinutes(1),
        LocalDateTime.now(), DATE_PARSE_PATTERN);

    assertThrows(InvalidParameterException.class, () -> new EventTask("", at),
        formatMessage("", at));
  }

  @Test
  public void atShouldNotBeEmptyString() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      assertThrows(InvalidParameterException.class, () -> new EventTask(description, ""),
          formatMessage(description, ""));
    }
  }

  @Test
  public void fromDateShouldNotBeAfterToDate() {
    // Format current datetime to a pattern which the command can parse.
    String at = DateRange.format(LocalDateTime.now().plusMinutes(1),
        LocalDateTime.now(), DATE_PARSE_PATTERN);

    assertThrows(InvalidParameterException.class, () -> new EventTask("one", at),
        formatMessage("", at));
  }

  @Test
  public void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      // Format current datetime to a pattern which the command can parse.
      String at = DateRange.format(LocalDateTime.now().minusMinutes(1),
          LocalDateTime.now(), DATE_PARSE_PATTERN);

      assertDoesNotThrow(() -> new EventTask(description, at),
          formatMessage(description, at));
    }
  }

  private String formatMessage(String description, String at) {
    return String.format("Input: new EventTask('%s','%s')", description, at);
  }
}
