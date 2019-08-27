package weomucat.duke.task.listener;

import weomucat.duke.task.TaskListTasks;

/**
 * When all tasks need to be listed, this listener will be notified.
 */
@FunctionalInterface
public interface ListTaskListener {
	/**
	 * When all tasks need to be listed, this method will be called.
	 * @param tasks an ArrayList of all tasks
	 */
	void listTaskUpdate(TaskListTasks tasks);
}
