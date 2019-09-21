package weomucat.doko.random;

import java.util.concurrent.ThreadLocalRandom;

public class RandomEpoch {

  // 9999-12-31T23:59:59
  private static final long MAX_EPOCH_SECONDS = 253402300799L;

  public static long generate() {
    return ThreadLocalRandom.current().nextLong(MAX_EPOCH_SECONDS);
  }
}
