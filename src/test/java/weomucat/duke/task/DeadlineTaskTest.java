package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;

public class DeadlineTaskTest {

  @Test
  public void descriptionShouldNotBeEmptyString() {
    Date by = Date.now();
    assertThrows(InvalidParameterException.class, () -> DeadlineTask.create("", by),
        formatMessage("", by.toString()));
  }

  @Test
  public void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      Date by = Date.now();
      assertDoesNotThrow(() -> DeadlineTask.create(description, by),
          formatMessage(description, by.toString()));
    }
  }

  private String formatMessage(String description, String by) {
    return String.format("Input: new DeadlineTask('%s','%s')", description, by);
  }
}
