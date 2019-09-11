package weomucat.duke.task.listener;

import weomucat.duke.task.Task;
import weomucat.duke.ui.Message;

/**
 * When a task is modified, this listener will be notified.
 * This includes creating and deleting of the task.
 */
@FunctionalInterface
public interface ModifyTaskListener {

  /**
   * When a task is modified, this method will be called.
   *
   * @param message message to display
   * @param task    the Task that was marked as done
   */
  void modifyTaskUpdate(Message message, Task task);
}
