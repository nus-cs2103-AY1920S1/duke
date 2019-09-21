package weomucat.doko.random;

import weomucat.doko.date.Date;
import weomucat.doko.date.DateRange;

public class RandomDateRange {

  /**
   * Generates a random DateRange.
   *
   * @return a random date range
   */
  public static DateRange generate() {
    Date from;
    Date to;
    do {
      from = RandomDate.generate();
      to = RandomDate.generate();
    } while (from.compareTo(to) >= 0);
    return new DateRange(from, to);
  }
}
