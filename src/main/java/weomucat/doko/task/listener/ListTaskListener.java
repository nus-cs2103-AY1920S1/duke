package weomucat.doko.task.listener;

import weomucat.doko.task.NumberedTaskList;
import weomucat.doko.ui.message.Message;

/**
 * When tasks need to be listed, this listener will be notified.
 */
@FunctionalInterface
public interface ListTaskListener extends TaskListener {

  /**
   * When tasks need to be listed, this method will be called.
   *
   * @param message message to display
   * @param tasks   an ArrayList of all tasks
   */
  void listTaskUpdate(Message message, NumberedTaskList tasks);
}
