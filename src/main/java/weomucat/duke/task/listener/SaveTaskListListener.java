package weomucat.duke.task.listener;

import weomucat.duke.exception.StorageException;
import weomucat.duke.task.TaskListTasks;

/**
 * When the task list needs to be saved, this listener will be notified.
 */
@FunctionalInterface
public interface SaveTaskListListener {

  /**
   * When a task list needs to be saved, this method will be called.
   *
   * @param tasks the task list
   * @throws StorageException if an error occurred while saving
   */
  void saveTaskListUpdate(TaskListTasks tasks) throws StorageException;
}
