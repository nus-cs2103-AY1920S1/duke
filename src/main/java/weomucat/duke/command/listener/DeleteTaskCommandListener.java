package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;

/**
 * When DeleteTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface DeleteTaskCommandListener {

  /**
   * When AddTaskCommand is run, this method will be called.
   *
   * @param i index of task to delete
   * @throws DukeException If there is anything wrong with processing.
   */
  void deleteTaskCommandUpdate(int i) throws DukeException;
}
