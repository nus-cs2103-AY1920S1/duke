public class DoneCommand extends Command {

	int index;

	public DoneCommand(int index) {
		super();
		this.index = index;
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (index > tasks.getSize()) {
			throw new DukeException("\u2639 OOPS!!! There is no such task in the list to mark as done.");
		}
		Task doneTask = tasks.getTask(index - 1);
		doneTask.markAsDone();
		ui.printDoneMessage(doneTask);
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
