package weomucat.doko.command.listener;

import weomucat.doko.exception.DokoException;

/**
 * When DeleteTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface DeleteTaskCommandListener extends CommandListener {

  /**
   * When AddTaskCommand is run, this method will be called.
   *
   * @param i index of task to delete
   * @throws DokoException If there is anything wrong with processing.
   */
  void deleteTaskCommandUpdate(int i) throws DokoException;
}
