package weomucat.doko.task.listener;

import weomucat.doko.exception.StorageException;
import weomucat.doko.task.TaskList;

/**
 * When the task list needs to be saved, this listener will be notified.
 */
public interface TaskListStorageListener extends TaskListener {

  /**
   * When a task list needs to be loaded, this method will be called.
   *
   * @throws StorageException if an error occurred while loading
   */
  TaskList loadTaskListUpdate() throws StorageException;

  /**
   * When a task list needs to be saved, this method will be called.
   *
   * @param tasks the task list
   * @throws StorageException if an error occurred while saving
   */
  void saveTaskListUpdate(TaskList tasks) throws StorageException;
}
