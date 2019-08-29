package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

/**
 * When a task is added, this listener will be notified.
 */
@FunctionalInterface
public interface AddTaskListener {

  /**
   * When a task is added, this method will be called.
   *
   * @param tasks an ArrayList of all tasks
   * @param task  the Task that was added
   * @throws DukeException If there is anything wrong with processing.
   */
  void addTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
