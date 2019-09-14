package weomucat.duke.task;

import weomucat.duke.date.Interval;

interface SnoozableTask {
  void snooze(Interval interval);
}
