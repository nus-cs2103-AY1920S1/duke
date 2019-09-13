package weomucat.duke.date;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import weomucat.duke.exception.InvalidParameterException;

/**
 * A Date is a given moment in time.
 */
public class Date implements Comparable<Date>, Serializable {

  // Pattern used to parse and format DateTime objects.
  static final String DATE_PARSE_PATTERN = "ddMMyy HHmm";
  private static final String DATE_FORMAT_PATTERN = "dd MMM yyyy, hh:mma, O";

  // Timezone that Duke uses.
  private static final ZoneId TIMEZONE = ZoneId.systemDefault();

  private static final String PARSE_ERROR_MESSAGE =
      String.format("I do not understand the date. Please enter in '%s' format.",
          DATE_PARSE_PATTERN);

  // Internally stored as ZonedDateTime object.
  private ZonedDateTime date;

  private Date(LocalDateTime date) {
    this.date = ZonedDateTime.of(date, TIMEZONE);
  }

  private Date(ZonedDateTime date) {
    this.date = date;
  }

  /**
   * Creates a Date object that represents the current date.
   *
   * @return the current date
   */
  public static Date now() {
    return new Date(LocalDateTime.now());
  }

  /**
   * Creates a Date object from a valid datetime string.
   *
   * @param dateTime the datetime string
   * @throws InvalidParameterException thrown if datetime string is invalid
   */
  public static Date parse(String dateTime) throws InvalidParameterException {
    try {
      // Parses the datetime string into a ZonedDateTime object.
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PARSE_PATTERN)
          .withZone(TIMEZONE);
      return new Date(ZonedDateTime.parse(dateTime.trim(), dateTimeFormatter));
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException(PARSE_ERROR_MESSAGE);
    }
  }

  /**
   * Adds a Duration to the current Date.
   *
   * @param duration the duration to add
   * @return new Date instance
   */
  public Date plus(Duration duration) {
    return new Date(this.date.plus(duration));
  }

  @Override
  public String toString() {
    // Format the ZonedDateTime object into a pretty datetime string.
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    return this.date.format(dateTimeFormatter);
  }

  @Override
  public int compareTo(Date o) {
    return this.date.compareTo(o.date);
  }
}
