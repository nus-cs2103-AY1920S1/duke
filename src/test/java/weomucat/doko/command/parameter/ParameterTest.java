package weomucat.doko.command.parameter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.doko.exception.InvalidParameterException;

class ParameterTest {

  @Test
  void requiredParameterShouldNotBeEmpty() {
    String[] invalid = {null, ""};
    for (String test : invalid) {
      assertThrows(InvalidParameterException.class, () -> new MockParameter(true).parse(test),
          formatMessage(true, test));
    }

    String[] valid = {" ", "one", "two", "three"};
    for (String test : valid) {
      MockParameter parameter = new MockParameter(true);
      assertDoesNotThrow(() -> parameter.parse(test),
          formatMessage(true, test));
      assertEquals("", parameter.value());
    }
  }

  @Test
  void optionalParameterValidUsage() {
    assertDoesNotThrow(() -> new MockParameter(false).parse(null),
        formatMessage(false, null));

    String[] valid = {"", " ", "one", "two", "three"};
    for (String test : valid) {
      MockParameter parameter = new MockParameter(false);
      assertDoesNotThrow(() -> parameter.parse(test),
          formatMessage(false, test));
      assertEquals("", parameter.value());
    }
  }

  private String formatMessage(boolean required, String input) {
    return String.format("Input: new MockParameter('%b').parse('%s')", required, input);
  }
}
