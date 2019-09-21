package weomucat.doko.date;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * A Date is a given moment in time.
 */
public class Date implements Comparable<Date>, Serializable {

  // Formatters used to format the stored instant.
  private static final DateTimeFormatter HUMAN_READABLE_FORMATTER =
      DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");
  private static final DateTimeFormatter ISO_8601_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

  // Timezone that Doko uses.
  private static ZoneId timezone = ZoneId.systemDefault();

  // Internally stored as Instant object, UTC timezone.
  private Instant date;

  /**
   * Creates a Date from an instant in UTC time.
   *
   * @param date an instant
   */
  public Date(Instant date) {
    this.date = date;
  }

  public static ZoneId timezone() {
    return timezone;
  }

  static void setTimezone(ZoneId zoneId) {
    timezone = zoneId;
  }

  /**
   * Creates a Date object that represents the current date.
   *
   * @return the current date
   */
  public static Date now() {
    return new Date(Instant.now());
  }

  /**
   * Adds a Duration to the current Date.
   *
   * @param interval the duration to add
   * @return new Date instance
   */
  public Date plus(Interval interval) {
    return new Date(this.date.plus(interval.duration));
  }

  /**
   * Converts the Date to a string which is ISO-8601 compliant.
   *
   * @return an ISO-8601 compliant string
   */
  public String toIso8601() {
    return ISO_8601_FORMATTER.withZone(timezone).format(this.date);
  }

  @Override
  public String toString() {
    // Format the stored date into a pretty datetime string.
    // Converts from UTC to timezone.
    return this.date.atZone(timezone).format(HUMAN_READABLE_FORMATTER);
  }

  @Override
  public int compareTo(Date o) {
    return this.date.compareTo(o.date);
  }
}
