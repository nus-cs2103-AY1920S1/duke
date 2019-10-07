package weomucat.doko.random;

import weomucat.doko.date.Date;

public class RandomDate {

  public static Date generate() {
    return new Date(RandomInstant.generate());
  }
}
