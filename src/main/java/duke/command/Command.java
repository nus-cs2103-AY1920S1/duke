package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command that handles requests in Duke.
 */
public abstract class Command {

	/**
	 * Constructor of Command.
	 */
	public Command() {
	}

	/**
	 * Executes specified command.
	 *
	 * @param tasks   Performs actions on TaskList if required.
	 * @param ui      Performs actions on Ui if required.
	 * @param storage Saves to Storage or loads from Storage if required.
	 * @return String representation of executed command.
	 */
	public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
