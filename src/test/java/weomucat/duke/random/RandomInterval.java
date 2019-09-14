package weomucat.duke.random;

import java.time.Duration;
import weomucat.duke.date.Interval;

public class RandomInterval {

  public static Interval generate() {
    return new Interval(Duration.ofSeconds(RandomEpoch.generate()));
  }
}
