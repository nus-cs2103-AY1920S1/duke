package weomucat.duke.random;

import weomucat.duke.date.Date;

public class RandomDate {

  public static Date generate() {
    return new Date(RandomInstant.generate());
  }
}
