package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

public interface DeleteTaskListener {
	void deleteTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
