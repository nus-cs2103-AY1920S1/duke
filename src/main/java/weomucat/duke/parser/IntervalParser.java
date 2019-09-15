package weomucat.duke.parser;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import weomucat.duke.date.Interval;
import weomucat.duke.exception.InvalidParameterException;

public class IntervalParser implements Parser<Interval> {

  // 5 Days, 12 Hours, 30 Minutes
  private static final long EXAMPLE_INTERVAL = 477000;
  private String input;

  public IntervalParser(String input) {
    this.input = input;
  }

  @Override
  public Interval parse() throws InvalidParameterException {
    try {
      return new Interval(Duration.parse(this.input));
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException(String.format("I do not understand the interval."
              + " Please enter in ISO-8601 format. Example: '%s'",
          new Interval(Duration.ofSeconds(EXAMPLE_INTERVAL)).toIso8601()));
    }
  }
}
