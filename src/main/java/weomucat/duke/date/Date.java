package weomucat.duke.date;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import weomucat.duke.exception.InvalidParameterException;

public class Date implements Serializable {

  // Pattern used to parse and format DateTime objects.
  public static final String DATE_PARSE_PATTERN = "ddMMyy HHmm";
  private static final String DATE_FORMAT_PATTERN = "dd MMM yyyy, hh:mma, O";

  // Timezone that Duke uses.
  private static final ZoneId TIMEZONE = ZoneId.systemDefault();

  private static final String PARSE_ERROR_MESSAGE =
      String.format("I do not understand the date. Please enter in '%s' format.",
          DATE_PARSE_PATTERN);

  // Internally stored as ZonedDateTime object.
  private ZonedDateTime date;

  /**
   * Creates a Date object from a valid datetime string.
   *
   * @param dateTime the datetime string
   * @throws InvalidParameterException thrown if datetime string is invalid
   */
  public Date(String dateTime) throws InvalidParameterException {
    // TODO: DateParser
    try {
      // Parses the datetime string into a ZonedDateTime object.
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSE_PATTERN)
          .withZone(TIMEZONE);
      this.date = ZonedDateTime.parse(dateTime.trim(), dateTimeFormatter);
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException(PARSE_ERROR_MESSAGE);
    }
  }

  /**
   * Formats a LocalDateTime using a pattern.
   *
   * @param date    the LocalDateTime to format
   * @param pattern the pattern to use
   * @return a formatted datetime string
   */
  public static String format(LocalDateTime date, String pattern) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    return date.format(dateTimeFormatter);
  }

  /**
   * Formats a ZonedDateTime using a pattern.
   *
   * @param date    the ZonedDateTime to format
   * @param pattern the pattern to use
   * @return a formatted datetime string
   */
  public static String format(ZonedDateTime date, String pattern) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    return date.format(dateTimeFormatter);
  }

  boolean isAfter(Date date) {
    return this.date.isAfter(date.date);
  }

  @Override
  public String toString() {
    // Format the ZonedDateTime object into a pretty datetime string.
    return Date.format(this.date, DATE_FORMAT_PATTERN);
  }
}
