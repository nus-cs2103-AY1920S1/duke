package weomucat.duke.parser;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import weomucat.duke.exception.InvalidParameterException;

/**
 * A DurationParser is able to parse a duration string in ISO-8601 format into a {@link Duration}.
 */
public class DurationParser {

  private String duration;

  /**
   * Creates a DurationParser.
   *
   * @param duration the duration in ISO-8601 format to parse
   */
  public DurationParser(String duration) {
    this.duration = duration;
  }

  /**
   * Parses the given duration string into a {@link Duration}.
   *
   * @return parsed Duration
   * @throws InvalidParameterException if unable to parse the duration, or the duration is negative
   */
  public Duration parse() throws InvalidParameterException {
    Duration duration;
    try {
      duration = Duration.parse(this.duration);
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException("I do not understand the duration."
          + "Please enter in ISO-8601 format.");
    }

    if (duration.isNegative()) {
      throw new InvalidParameterException("The duration must be positive!");
    }

    return duration;
  }
}
