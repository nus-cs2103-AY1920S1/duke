package weomucat.doko.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.doko.exception.DokoRuntimeException;

class TodoTaskTest {

  @Test
  void descriptionShouldNotBeEmptyString() {
    assertThrows(DokoRuntimeException.class, () -> new TodoTask(""), formatMessage(""));
  }

  @Test
  void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      assertDoesNotThrow(() -> new TodoTask(description), formatMessage(description));
    }
  }

  private String formatMessage(String description) {
    return String.format("Input: new TodoTask('%s')", description);
  }
}
