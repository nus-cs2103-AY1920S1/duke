/**
 * Represents the command to list all tasks in memory and their respective details
 */
public class ListCommand extends Command {
	/**
	 * just a default constructor for the list command object
	 */
	public ListCommand() {
		super();
	}

	/**
	 * simply converts the tasks in TaskList to string and displays them to the user through the UI object
	 * @param tasks represents all the tasks added in memory
	 * @param ui represents the interaction between duke and the user
	 * @param storage represents the reading and writing to the archival file
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.displayLine(tasks.toString());
	}
}
