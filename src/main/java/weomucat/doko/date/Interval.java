package weomucat.doko.date;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import weomucat.doko.exception.DokoRuntimeException;

/**
 * An interval is a duration of time.
 */
public class Interval implements Serializable {

  // Patterns used to format the stored duration.
  private static final String ISO_8601_PATTERN = "P%sDT%sH%sM";

  // Internally stored as Duration
  Duration duration;

  /**
   * Creates an Interval from a duration.
   *
   * @param duration a duration
   */
  public Interval(Duration duration) {
    if (duration.isNegative()) {
      throw new DokoRuntimeException("The interval must be positive!");
    }
    this.duration = duration;
  }

  /**
   * Converts the Interval to a string which is ISO-8601 compliant.
   *
   * @return an ISO-8601 compliant string
   */
  public String toIso8601() {
    return String.format(ISO_8601_PATTERN,
        this.duration.toDaysPart(),
        this.duration.toHoursPart(),
        this.duration.toMinutesPart());
  }

  @Override
  public String toString() {
    long days = this.duration.toDaysPart();
    int hours = this.duration.toHoursPart();
    int minutes = this.duration.toMinutesPart();

    ArrayList<String> result = new ArrayList<>();
    if (days > 0) {
      result.add(days + " Day(s)");
    }
    if (hours > 0) {
      result.add(hours + " Hour(s)");
    }
    if (minutes > 0) {
      result.add(minutes + " Min(s)");
    }
    return String.join(", ", result);
  }
}
