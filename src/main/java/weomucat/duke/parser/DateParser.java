package weomucat.duke.parser;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;

public class DateParser {

  private static final DateTimeFormatter DATE_PARSER = ISO_DATE_TIME;
  private String input;

  public DateParser(String input) {
    this.input = input.trim();
  }

  /**
   * Parses user input into a Date.
   *
   * @return a date
   * @throws InvalidParameterException if the input is not in iso-8601 format
   */
  public Date parse() throws InvalidParameterException {
    try {
      // Add timezone information to parser.
      // Will be used if user does not supply timezone.
      // Parsed date is then converted to UTC.
      DateTimeFormatter parser = DATE_PARSER.withZone(Date.timezone());
      return new Date(Instant.from(parser.parse(this.input)));
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException(String.format(
          "I do not understand the date. Please enter in 'ISO-8601' format. Example: '%s'",
          Date.now().toIso8601()));
    }
  }
}
