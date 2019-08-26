package weomucat.duke;

public interface DeleteTaskListener {
	void deleteTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
