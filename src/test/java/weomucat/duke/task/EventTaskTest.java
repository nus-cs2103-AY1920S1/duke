package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.duke.exception.InvalidParameterException;

public class EventTaskTest {

  @Test
  public void descriptionShouldNotBeEmptyString() {
    String[] locations = {"one", "one two", "one two three"};

    for (String location : locations) {
      assertThrows(InvalidParameterException.class, () -> new EventTask("", location),
          formatMessage("", location));
    }
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
  public void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};
    String[] locations = {"four", "four five", "four five six"};

    for (int i = 0; i < descriptions.length; i++) {
      String description = descriptions[i];
      String location = locations[i];
      assertDoesNotThrow(() -> new EventTask(description, location),
          formatMessage(description, location));
    }
  }

  private String formatMessage(String description, String at) {
    return String.format("Input: new EventTask('%s','%s')", description, at);
  }
}
