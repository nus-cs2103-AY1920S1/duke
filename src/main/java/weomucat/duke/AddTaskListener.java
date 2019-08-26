package weomucat.duke;

public interface AddTaskListener {
	void addTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
