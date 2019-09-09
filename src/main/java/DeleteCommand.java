import java.util.Scanner;

/**
 * Represents the delete task functionality of duke
 */
public class DeleteCommand extends Command {

	/**
	 * Constructor of Delete Command
	 * @param inFullCommandScanner scanner to scan the user's desired task index to delete
	 */
	public DeleteCommand(Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
	}

	/**
	 * Executes the delete command to remove the task from taskList
	 * @param tasks represents all the tasks added in memory
	 * @param ui represents the interaction between duke and the user
	 * @param storage represents the reading and writing to the archival file
	 * @throws DukeException
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (!inFullCommandScanner.hasNext()) {
			throw new DukeException("no number provided");
		}
		Integer taskToDeleteIndex = Integer.parseInt(inFullCommandScanner.next());
		assert (taskToDeleteIndex != null);
		assert (taskToDeleteIndex != 0);
		Task taskToBeDeleted;
		try {
			taskToBeDeleted = tasks.removeTask(taskToDeleteIndex - 1);
			StringBuilder output = new StringBuilder();
			output.append("Noted. I've removed this task:\n");
			output.append("    " + taskToBeDeleted + "\n");
			output.append(String.format("Now you have %d tasks in the list.", tasks.getTaskListSize()));
			ui.displayLine(output.toString());
		} catch (Exception e) {
			throw new DukeException(e.getMessage());
		}
	}
}
