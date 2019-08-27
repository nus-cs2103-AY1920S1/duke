package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command.
 * Abstract parent class of all specific types of <code>Command</code>.
 * A <code>Command</code> object corresponds to an action that can be executed.
 */
public abstract class Command {
	/**
	 * Performs an action corresponding to the specific <code>Command</code>>.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 * @throws DukeException If provided details are insufficient or invalid.
	 */
	public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

	/**
	 * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
	 */
	public abstract boolean isExit();
}
