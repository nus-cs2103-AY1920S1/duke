package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.duke.date.Date;
import weomucat.duke.exception.DukeRuntimeException;

class DeadlineTaskTest {

  @Test
  void descriptionShouldNotBeEmptyString() {
    Date by = Date.now();
    assertThrows(DukeRuntimeException.class, () -> new DeadlineTask("", by, null),
        formatMessage("", by.toString()));
  }

  @Test
  void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      Date by = Date.now();
      assertDoesNotThrow(() -> new DeadlineTask(description, by, null),
          formatMessage(description, by.toString()));
    }
  }

  private String formatMessage(String description, String by) {
    return String.format("Input: new DeadlineTask('%s','%s')", description, by);
  }
}
