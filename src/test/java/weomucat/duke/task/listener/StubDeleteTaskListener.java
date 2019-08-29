package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

public class StubDeleteTaskListener implements DeleteTaskListener {

  private Task task;
  private TaskListTasks tasks;

  @Override
  public void deleteTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    this.task = task;
    this.tasks = tasks;
  }

  public Task getTask() {
    return task;
  }

  public TaskListTasks getTasks() {
    return tasks;
  }
}
