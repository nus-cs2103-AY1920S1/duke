/**
 * Represents the command to exit the Duke program
 */
public class ExitCommand extends Command {
	/**
	 * Just a default constructor for the Exit Command
	 */
	public ExitCommand() {
		super();
	}

	/**
	 * Executes the exit command, which does 2 things: save all tasks into storage and displays the exit messsage
	 * 		through the UI object
	 * @param tasks represents all the tasks added in memory
	 * @param ui represents the interaction between duke and the user
	 * @param storage represents the reading and writing to the archival file
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		super.isExit = true;
		storage.saveTasks(tasks);
		ui.showExitMessage();
	}
}
