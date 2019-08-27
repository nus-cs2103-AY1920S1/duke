package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a <code>Command</code> that deletes a <code>Task</code> from the <code>TaskList</code>.
 */
public class DeleteCommand extends Command {
	int index;

	/**
	 * Constructor for <Code>DeleteCommand</Code>.
	 * @param index The index of the <code>Task</code> in the <code>TaskList</code>.
	 */
	public DeleteCommand(int index) {
		super();
		this.index = index;
	}

	/**
	 * Deletes a  <code>Task</code> object in the <code>TaskList</code> that corresponds to the provided index.
	 * Calls methods in <code>Storage</code> and <code>Ui</code> to write the updated <code>TaskList</code> to hard
	 * disk and print message to console respectively.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 * @throws DukeException If provided index is missing or invalid.
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (index > tasks.getSize()) {
			throw new DukeException("\u2639 OOPS!!! There is no such task in the list to delete.");
		}
		Task deletedTask = tasks.getTask(index - 1);
		tasks.deleteTask(index - 1);
		ui.printDeleteMessage(deletedTask, tasks.getSize());
		try {
			storage.writeToFile(tasks);
		} catch (DukeException exception) {
			ui.printExceptionMessage(exception);
		}
	}

	/**
	 * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
	 * @return False.
	 */
	public boolean isExit() {
		return false;
	}

	/**
	 * Returns provided index.
	 * @return Provided index.
	 */
	public int getIndex() {
		return index;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof DeleteCommand) {
			DeleteCommand deleteCommand = (DeleteCommand) o;
			return deleteCommand.getIndex() == index;
		} else {
			return false;
		}
	}
}
