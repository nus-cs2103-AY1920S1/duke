package weomucat.duke.task.listener;

import weomucat.duke.task.TaskListTasks;
import weomucat.duke.ui.Message;

public class StubListTaskListener implements ListTaskListener {

  private TaskListTasks tasks;

  @Override
  public void listTaskUpdate(Message message, TaskListTasks tasks) {
    this.tasks = tasks;
  }

  public TaskListTasks getTasks() {
    return tasks;
  }
}
