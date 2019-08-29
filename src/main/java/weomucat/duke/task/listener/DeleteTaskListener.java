package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

/**
 * When a task is deleted, this listener will be notified.
 */
@FunctionalInterface
public interface DeleteTaskListener {

  /**
   * When a task is deleted, this method will be called.
   *
   * @param tasks an ArrayList of all tasks
   * @param task  the Task that was deleted
   * @throws DukeException If there is anything wrong with processing.
   */
  void deleteTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
