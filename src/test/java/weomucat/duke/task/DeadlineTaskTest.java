package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static weomucat.duke.date.Date.DATETIME_PARSE_PATTERN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import weomucat.duke.exception.InvalidParameterException;

public class DeadlineTaskTest {

  @Test
  public void descriptionShouldNotBeEmptyString() {
    // TODO: Date.format()
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_PATTERN);
    String by = LocalDateTime.now().format(dateTimeFormatter);

    assertThrows(InvalidParameterException.class, () -> new DeadlineTask("", by),
        formatMessage("", by));
  }

  @Test
  public void byShouldBeProperlyFormatted() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      assertThrows(InvalidParameterException.class, () -> new DeadlineTask(description, ""),
          formatMessage(description, ""));
    }
  }

  @Test
  public void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_PATTERN);

    for (String description : descriptions) {
      String by = LocalDateTime.now().format(dateTimeFormatter);
      assertDoesNotThrow(() -> new DeadlineTask(description, by), formatMessage(description, by));
    }
  }

  private String formatMessage(String description, String by) {
    return String.format("Input: new DeadlineTask('%s','%s')", description, by);
  }
}
