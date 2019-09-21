package weomucat.doko.random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import weomucat.doko.date.DateRange;

public class RandomDateRanges {

  private static final int MIN = 1;
  private static final int MAX = 10;

  /**
   * Generates a random amount of DateRanges.
   *
   * @return a random amount of date ranges
   */
  public static List<DateRange> generate() {
    ArrayList<DateRange> result = new ArrayList<>();
    for (int i = 0; i < ThreadLocalRandom.current().nextInt(MIN, MAX); i++) {
      result.add(RandomDateRange.generate());
    }
    return result;
  }
}
