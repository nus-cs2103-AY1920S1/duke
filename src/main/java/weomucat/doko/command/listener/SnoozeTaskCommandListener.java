package weomucat.doko.command.listener;

import weomucat.doko.date.Interval;
import weomucat.doko.exception.DokoException;

/**
 * When SnoozeTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface SnoozeTaskCommandListener extends CommandListener {

  /**
   * When SnoozeTaskCommand is run, this method will be called.
   *
   * @param taskIndex the index of the task to snooze
   * @param interval  the duration to snooze the task for
   */
  void snoozeTaskCommandUpdate(int taskIndex, Interval interval) throws DokoException;
}
