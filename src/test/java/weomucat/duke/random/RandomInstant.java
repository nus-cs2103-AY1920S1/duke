package weomucat.duke.random;

import java.time.Instant;

public class RandomInstant {

  public static Instant generate() {
    return Instant.ofEpochSecond(RandomEpoch.generate());
  }
}
