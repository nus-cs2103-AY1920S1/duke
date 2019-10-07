package weomucat.doko.command.listener;

import weomucat.doko.exception.DokoException;

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
   * @throws DokoException If there is anything wrong with processing.
   */
  void eventAtCommandUpdate(int taskIndex, int atIndex) throws DokoException;
}
