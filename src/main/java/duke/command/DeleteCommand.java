package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {

	int index;

	public DeleteCommand(int index) {
		super();
		this.index = index;
	}

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

	public boolean isExit() {
		return false;
	}

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
