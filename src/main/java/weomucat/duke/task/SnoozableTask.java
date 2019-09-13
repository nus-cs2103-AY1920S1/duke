package weomucat.duke.task;

import java.time.Duration;

interface SnoozableTask {
  void snooze(Duration duration);
}
