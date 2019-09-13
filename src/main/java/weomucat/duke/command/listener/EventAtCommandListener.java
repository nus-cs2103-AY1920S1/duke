package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;

/**
 * When EventAtCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface EventAtCommandListener extends CommandListener {

  /**
   * When EventAtCommand is run, this method will be called.
   *
   * @param taskIndex the index of the event in the task list
   * @param atIndex   the index of the schedule in the tentative schedule list
   * @throws DukeException If there is anything wrong with processing.
   */
  void eventAtCommandUpdate(int taskIndex, int atIndex) throws DukeException;
}
