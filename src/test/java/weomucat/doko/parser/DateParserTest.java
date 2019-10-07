package weomucat.doko.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weomucat.doko.date.Date;
import weomucat.doko.exception.InvalidParameterException;

class DateParserTest {

  private static final int RANDOM_TESTS = 5;

  @Test
  void invalidUsage() {
    String[] tests = {"", " ", "one", "two", "three"};
    for (String test : tests) {
      assertThrows(InvalidParameterException.class, () -> new DateParser(test).parse(),
          formatMessage(test));
    }
  }

  @Test
  void validUsage() {
    for (int i = 0; i < RANDOM_TESTS; i++) {
      String test = Date.now().toIso8601();
      assertDoesNotThrow(() -> new DateParser(test).parse(),
          formatMessage(test));
    }
  }

  private String formatMessage(String input) {
    return String.format("Input: new DateParser('%s').parse()", input);
  }
}
