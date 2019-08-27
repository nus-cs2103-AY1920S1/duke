package weomucat.duke.task.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;

/**
 * When a task is marked as done, this listener will be notified.
 */
@FunctionalInterface
public interface DoneTaskListener {
	/**
	 * When a task is marked as done, this method will be called.
	 * @param tasks an ArrayList of all tasks
	 * @param task the Task that was marked as done
	 * @throws DukeException If there is anything wrong with processing.
	 */
	void doneTaskUpdate(TaskListTasks tasks, Task task) throws DukeException;
}
