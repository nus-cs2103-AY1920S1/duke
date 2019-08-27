public class ExitCommand extends Command {
	public ExitCommand() {
		super();
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		super.isExit = true;
		storage.saveTasks(tasks);
		ui.showExitMessage();
	}
}
