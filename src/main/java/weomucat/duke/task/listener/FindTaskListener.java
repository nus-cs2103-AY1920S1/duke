package weomucat.duke.task.listener;

import weomucat.duke.task.TaskListTasks;

/**
 * When tasks are searched, this listener will be notified.
 */
@FunctionalInterface
public interface FindTaskListener {

  /**
   * When tasks are searched, this method will be called.
   *
   * @param tasks an ArrayList of tasks found
   */
  void findTaskUpdate(TaskListTasks tasks);
}
