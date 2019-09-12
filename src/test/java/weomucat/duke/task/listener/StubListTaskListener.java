package weomucat.duke.task.listener;

import weomucat.duke.task.NumberedTaskList;
import weomucat.duke.task.TaskList;
import weomucat.duke.ui.Message;

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
