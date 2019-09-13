package weomucat.duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import weomucat.duke.date.Date;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.DukeRuntimeException;

class EventTaskTest {

  @Test
  void descriptionShouldNotBeEmptyString() {
    DateRange at = assertDoesNotThrow(() -> DateRange.create(Date.now(),
        Date.now().plus(Duration.ofMinutes(1))));

    assertThrows(DukeRuntimeException.class,
        () -> new EventTask("", Collections.singleton(at), null),
        formatMessage("", at));
  }

  @Test
  void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      DateRange at = assertDoesNotThrow(() -> DateRange.create(Date.now(),
          Date.now().plus(Duration.ofMinutes(1))));

      assertDoesNotThrow(() -> new EventTask(description, Collections.singleton(at), null),
          formatMessage(description, at));
    }
  }

  private String formatMessage(String description, DateRange at) {
    return String.format("Input: new EventTask('%s','%s')", description, at);
  }
}
