package weomucat.duke.date;

import static weomucat.duke.date.Date.DATE_PARSE_PATTERN;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import weomucat.duke.exception.InvalidParameterException;

/**
 * A DateRange is a span of time between two Dates.
 */
public class DateRange implements Serializable {

  private static final String DELIMITER = "-";

  private static final String PARSE_ERROR_MESSAGE =
      String.format("Not enough dates in date range. Please enter in '%s %s %s' format.",
          DATE_PARSE_PATTERN, DELIMITER, DATE_PARSE_PATTERN);

  private Date from;
  private Date to;

  private DateRange(Date from, Date to) {
    this.from = from;
    this.to = to;
  }

  /**
   * Creates a DateRange from two Dates.
   *
   * @param from start Date
   * @param to   end Date
   * @throws InvalidParameterException if start date is after end date
   */
  public static DateRange create(Date from, Date to) throws InvalidParameterException {
    if (from.compareTo(to) >= 0) {
      throw new InvalidParameterException("The start date must come before the end date.");
    }
    return new DateRange(from, to);
  }

  /**
   * Creates a DateRange object from two datetime strings separated by a delimiter.
   *
   * @param range two datetime strings separated by a delimiter
   * @throws InvalidParameterException thrown if range is invalid
   */
  public static DateRange parse(String range) throws InvalidParameterException {
    String[] dates = range.split(DELIMITER);
    if (dates.length < 2) {
      throw new InvalidParameterException(PARSE_ERROR_MESSAGE);
    }

    Date from = Date.parse(dates[0]);
    Date to = Date.parse(dates[1]);
    return new DateRange(from, to);
  }

  /**
   * Formats two LocalDateTimes using a pattern.
   *
   * @param from    the start of the date range
   * @param to      the end of the date range
   * @param pattern the pattern to use
   * @return a formatted datetime string
   */
  public static String format(LocalDateTime from, LocalDateTime to, String pattern) {
    return String.format("%s %s %s", Date.format(from, pattern), DELIMITER,
        Date.format(to, pattern));
  }

  public DateRange plus(Duration duration) {
    Date from = this.from.plus(duration);
    Date to = this.to.plus(duration);
    return new DateRange(from, to);
  }

  public Date getFrom() {
    return from;
  }

  public Date getTo() {
    return to;
  }

  @Override
  public String toString() {
    return String.format("%s - %s", from, to);
  }
}
