package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

public class StubAddTaskListener implements AddTaskListener {

  private Task task;
  private TaskListTasks tasks;

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) throws DukeException {
    this.task = task;
    this.tasks = tasks;
  }

  public Task getTask() {
    return this.task;
  }

  public TaskListTasks getTasks() {
    return this.tasks;
  }
}
