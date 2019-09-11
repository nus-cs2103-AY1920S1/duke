package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static weomucat.duke.date.Date.DATE_PARSE_PATTERN;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;

public class DeadlineTaskTest {

  @Test
  public void descriptionShouldNotBeEmptyString() {
    // Format current datetime to a pattern which the command can parse.
    String by = Date.format(LocalDateTime.now(), DATE_PARSE_PATTERN);

    assertThrows(InvalidParameterException.class, () -> new DeadlineTask("", by),
        formatMessage("", by));
  }

  @Test
  public void byShouldNotBeEmptyString() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      assertThrows(InvalidParameterException.class, () -> new DeadlineTask(description, ""),
          formatMessage(description, ""));
    }
  }

  @Test
  public void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      // Format current datetime to a pattern which the command can parse.
      String by = Date.format(LocalDateTime.now(), DATE_PARSE_PATTERN);

      assertDoesNotThrow(() -> new DeadlineTask(description, by), formatMessage(description, by));
    }
  }

  private String formatMessage(String description, String by) {
    return String.format("Input: new DeadlineTask('%s','%s')", description, by);
  }
}
