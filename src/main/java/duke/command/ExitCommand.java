package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a <code>Command</code> that closes the program.
 */
public class ExitCommand extends Command {
	/**
	 * Constructor for <code>ExitCommand</code>.
	 */
	public ExitCommand() {
		super();
	}

	/**
	 * Closes the program by closing the <code>Scanner</code> in <code>Ui</code>.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printExitMessage();
		ui.closeScanner();
	}

	/**
	 * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
	 * @return True.
	 */
	public boolean isExit() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof ExitCommand) {
			return true;
		} else {
			return false;
		}
	}
}
