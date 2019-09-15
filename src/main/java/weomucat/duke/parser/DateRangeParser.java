package weomucat.duke.parser;

import java.time.Instant;
import weomucat.duke.date.Date;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;

public class DateRangeParser implements Parser<DateRange> {

  public static final String DATE_RANGE_DELIMITER = " - ";
  private static final int EXAMPLE_SECONDS = 30 * 60;

  private String input;

  DateRangeParser(String input) {
    this.input = input.trim();
  }

  @Override
  public DateRange parse() throws InvalidParameterException {
    String[] dates = input.split(DATE_RANGE_DELIMITER);
    if (dates.length < 2) {
      throw new InvalidParameterException(
          String.format("Not enough dates in date range."
                  + " Please enter in 'ISO-8601%sISO-8601' format. Example: '%s'",
              DATE_RANGE_DELIMITER,
              new DateRange(
                  new Date(Instant.now().minusSeconds(EXAMPLE_SECONDS)),
                  new Date(Instant.now())
              ).toIso8601()));
    }

    Date from = new DateParser(dates[0]).parse();
    Date to = new DateParser(dates[1]).parse();
    return new DateRange(from, to);
  }
}
