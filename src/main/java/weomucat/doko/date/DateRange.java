package weomucat.doko.date;

import static weomucat.doko.parser.DateRangeParser.DATE_RANGE_DELIMITER;

import java.io.Serializable;
import weomucat.doko.exception.DokoRuntimeException;

/**
 * A DateRange is a span of time between two Dates.
 */
public class DateRange implements Serializable {

  private Date from;
  private Date to;

  /**
   * Creates a DateRange from two Dates.
   *
   * @param from start Date
   * @param to   end Date
   */
  public DateRange(Date from, Date to) {
    if (from.compareTo(to) >= 0) {
      throw new DokoRuntimeException("The start date must come before the end date.");
    }
    this.from = from;
    this.to = to;
  }

  /**
   * Adds a duration to this DateRange.
   *
   * @param interval the duration to add
   * @return new DateRange instance
   */
  public DateRange plus(Interval interval) {
    Date from = this.from.plus(interval);
    Date to = this.to.plus(interval);
    return new DateRange(from, to);
  }

  public Date getFrom() {
    return from;
  }

  public Date getTo() {
    return to;
  }

  /**
   * Converts the Date Range to a string which is ISO-8601 compliant.
   *
   * @return an ISO-8601 compliant string
   */
  public String toIso8601() {
    return String.format("%s%s%s", this.from.toIso8601(), DATE_RANGE_DELIMITER,
        this.to.toIso8601());
  }

  @Override
  public String toString() {
    return String.format("%s - %s", from, to);
  }
}
