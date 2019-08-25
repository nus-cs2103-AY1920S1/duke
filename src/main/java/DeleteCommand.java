import java.util.ArrayList;

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

}
