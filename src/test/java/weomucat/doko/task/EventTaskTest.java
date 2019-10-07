package weomucat.doko.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import weomucat.doko.date.DateRange;
import weomucat.doko.exception.DokoRuntimeException;
import weomucat.doko.random.RandomDateRange;

class EventTaskTest {

  private static final int RANDOM_TESTS = 5;

  @Test
  void descriptionShouldNotBeEmptyString() {
    for (int i = 0; i < RANDOM_TESTS; i++) {
      DateRange dateRange = RandomDateRange.generate();
      assertThrows(DokoRuntimeException.class,
          () -> new EventTask("", Collections.singleton(dateRange), null),
          formatMessage("", dateRange));
    }
  }

  @Test
  void validUsage() {
    String[] descriptions = {"one", "one two", "one two three"};

    for (String description : descriptions) {
      DateRange dateRange = RandomDateRange.generate();
      assertDoesNotThrow(() ->
              new EventTask(description, Collections.singleton(dateRange), null),
          formatMessage(description, dateRange));
    }
  }

  private String formatMessage(String description, DateRange at) {
    return String.format("Input: new EventTask('%s','%s')", description, at);
  }
}
