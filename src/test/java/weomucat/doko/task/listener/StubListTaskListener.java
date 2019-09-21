package weomucat.doko.task.listener;

import weomucat.doko.task.NumberedTaskList;
import weomucat.doko.ui.message.Message;

public class StubListTaskListener implements ListTaskListener {

  private NumberedTaskList tasks;

  @Override
  public void listTaskUpdate(Message message, NumberedTaskList tasks) {
    this.tasks = tasks;
  }

  public NumberedTaskList getTasks() {
    return tasks;
  }
}
