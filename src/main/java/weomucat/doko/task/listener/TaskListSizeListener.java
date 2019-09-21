package weomucat.doko.task.listener;

/**
 * When the size of a task list changes, this listener will be notified.
 */
@FunctionalInterface
public interface TaskListSizeListener extends TaskListener {

  /**
   * When the size of a task list changes, this method will be called.
   *
   * @param size new size of the task list
   */
  void taskListSizeUpdate(int size);
}
