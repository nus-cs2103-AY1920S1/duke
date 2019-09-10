package weomucat.duke.date;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import weomucat.duke.exception.InvalidParameterException;

public class Date {

  // Pattern used to parse and format DateTime objects.
  public static final String DATETIME_PARSE_PATTERN = "ddMMyy HHmm";
  private static final String DATETIME_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

  // Timezone that Duke uses.
  private static final ZoneId TIMEZONE = ZoneId.systemDefault();

  private static final String PARSE_ERROR_MESSAGE =
      String.format("I do not understand the date. Please enter in '%s' format.",
          DATETIME_PARSE_PATTERN);

  // Internally stored as ZonedDateTime object.
  private ZonedDateTime date;

  /**
   * Creates a Date object from a valid datetime string.
   *
   * @param dateTime the datetime string
   * @throws InvalidParameterException thrown if datetime string is invalid
   */
  public Date(String dateTime) throws InvalidParameterException {
    try {
      // Parses the datetime string into a ZonedDateTime object.
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_PARSE_PATTERN)
          .withZone(TIMEZONE);
      this.date = ZonedDateTime.parse(dateTime, dateTimeFormatter);
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException(PARSE_ERROR_MESSAGE);
    }
  }

  @Override
  public String toString() {
    // Formats the ZonedDateTime object into a pretty datetime string.
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);
    return this.date.format(dateTimeFormatter);
  }
}
