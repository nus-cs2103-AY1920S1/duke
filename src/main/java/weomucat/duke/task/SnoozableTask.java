package weomucat.duke.task;

import java.time.Duration;

public interface SnoozableTask {
  void snooze(Duration duration);
}
