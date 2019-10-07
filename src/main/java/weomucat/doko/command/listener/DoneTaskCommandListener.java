package weomucat.doko.command.listener;

import weomucat.doko.exception.DokoException;

/**
 * When DoneTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface DoneTaskCommandListener extends CommandListener {

  /**
   * When DoneTaskCommand is run, this method will be called.
   *
   * @param i index of the task to mark as done
   * @throws DokoException If there is anything wrong with processing.
   */
  void doneTaskCommandUpdate(int i) throws DokoException;
}
