package weomucat.duke.command.listener;

import java.time.Duration;
import weomucat.duke.exception.DukeException;

/**
 * When SnoozeTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface SnoozeTaskCommandListener {

  /**
   * When SnoozeTaskCommand is run, this method will be called.
   *
   * @param taskIndex the index of the task to snooze
   * @param duration  the duration to snooze the task for
   */
  void snoozeTaskCommandUpdate(int taskIndex, Duration duration) throws DukeException;
}
