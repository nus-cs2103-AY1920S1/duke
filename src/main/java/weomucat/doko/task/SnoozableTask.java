package weomucat.doko.task;

import weomucat.doko.date.Interval;

interface SnoozableTask {
  void snooze(Interval interval);
}
