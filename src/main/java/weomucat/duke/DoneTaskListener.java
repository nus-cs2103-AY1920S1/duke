package weomucat.duke;

public interface DoneTaskListener {
	void doneTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
