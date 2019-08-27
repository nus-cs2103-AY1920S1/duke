package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;

/**
 * When AddTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface AddTaskCommandListener {
	/**
	 * When AddTaskCommand is run, this method will be called.
	 * @param task the Task that was added
	 * @throws DukeException If there is anything wrong with processing.
	 */
	void addTaskCommandUpdate(Task task) throws DukeException;
}
