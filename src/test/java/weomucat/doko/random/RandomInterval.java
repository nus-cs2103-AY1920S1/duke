package weomucat.doko.random;

import java.time.Duration;
import weomucat.doko.date.Interval;

public class RandomInterval {

  public static Interval generate() {
    return new Interval(Duration.ofSeconds(RandomEpoch.generate()));
  }
}
