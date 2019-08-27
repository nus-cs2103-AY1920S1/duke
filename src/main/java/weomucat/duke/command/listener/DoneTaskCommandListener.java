package weomucat.duke.command.listener;

import weomucat.duke.exception.DukeException;

/**
 * When DoneTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface DoneTaskCommandListener {
	/**
	 * When DoneTaskCommand is run, this method will be called.
	 * @param i index of the task to mark as done
	 * @throws DukeException If there is anything wrong with processing.
	 */
	void doneTaskCommandUpdate(int i) throws DukeException;
}
