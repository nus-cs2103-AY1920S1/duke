package weomucat.doko.task.listener;

import weomucat.doko.task.Task;
import weomucat.doko.ui.message.Message;

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
