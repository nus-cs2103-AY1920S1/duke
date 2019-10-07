package weomucat.doko.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static weomucat.doko.parser.DateRangesParser.DATE_RANGES_DELIMITER;

import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import weomucat.doko.date.DateRange;
import weomucat.doko.exception.InvalidParameterException;
import weomucat.doko.random.RandomDateRanges;

class DateRangesParserTest {

  private static final int RANDOM_TESTS = 5;

  @Test
  void invalidUsage() {
    String[] tests = {"", " ", "one", "two", "three"};
    for (String test : tests) {
      assertThrows(InvalidParameterException.class, () -> new DateRangesParser(test).parse(),
          formatMessage(test));
    }
  }

  @Test
  void validUsage() {
    for (int i = 0; i < RANDOM_TESTS; i++) {
      String test = RandomDateRanges.generate().stream()
          .map(DateRange::toIso8601)
          .collect(Collectors.joining(DATE_RANGES_DELIMITER));
      assertDoesNotThrow(() -> new DateRangesParser(test).parse(),
          formatMessage(test));
    }
  }

  private String formatMessage(String input) {
    return String.format("Input: new DateRangesParser('%s').parse()", input);
  }
}
