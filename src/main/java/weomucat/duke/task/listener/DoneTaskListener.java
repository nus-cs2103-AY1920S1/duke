package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

public interface DoneTaskListener {
	void doneTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
