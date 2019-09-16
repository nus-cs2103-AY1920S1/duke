package weomucat.duke.task.listener;

import weomucat.duke.task.Task;
import weomucat.duke.ui.message.Message;

public class StubModifyTaskListener implements ModifyTaskListener {

  private Task task;


  @Override
  public void modifyTaskUpdate(Message message, Task task) {
    this.task = task;

  }

  public Task getTask() {
    return this.task;
  }
}
