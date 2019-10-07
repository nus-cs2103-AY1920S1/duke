package weomucat.doko.command.listener;

import weomucat.doko.exception.DokoException;
import weomucat.doko.task.Task;

/**
 * When AddTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface AddTaskCommandListener extends CommandListener {

  /**
   * When AddTaskCommand is run, this method will be called.
   *
   * @param task the Task that was added
   * @throws DokoException If there is anything wrong with processing.
   */
  void addTaskCommandUpdate(Task task) throws DokoException;
}
