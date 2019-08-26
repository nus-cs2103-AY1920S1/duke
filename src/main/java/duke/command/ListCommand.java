package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a <code>Command</code> that prints every <code>Task</code> object in the <code>TaskList</code>.
 */
public class ListCommand extends Command {

	/**
	 * Constructor for <code>ListCommand</code>.
	 */
	public ListCommand() {
		super();
	}
	/**
	 * Prints every <code>Task</code> object in the <code>TaskList</code> using a method in <code>Ui</code>.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printMessage("\t Here are the tasks in your list:");
		for (int i = 1; i <= tasks.getSize(); i++) {
			ui.printMessage("\t " + i + ". " + tasks.getTask(i - 1));
		}
	}

	/**
	 * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
	 * @return False.
	 */
	public boolean isExit() {
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof ListCommand) {
			return true;
		} else {
			return false;
		}
	}

}
