import java.util.Scanner;

/**
 * Represents duke's functionality of marking a task as completed
 */
public class DoneCommand extends Command {
	/**
	 * Constructor of done command
	 * @param inFullCommandScanner scanner used to read the index of the task which the user wishes to mark as done
	 */
	public DoneCommand(Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
	}

	/**
	 * Marks the task specified as completed
	 * @param tasks represents all the tasks added in memory
	 * @param ui represents the interaction between duke and the user
	 * @param storage represents the reading and writing to the archival file
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		//read which task to complete and set as complete
		Integer taskNumber = Integer.parseInt(inFullCommandScanner.nextLine().trim()) - 1;
		assert(taskNumber != null);
		assert(taskNumber != 0);
		Task task = tasks.markCompleted(taskNumber);
		StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
		sb.append("    ");
		sb.append(task.toString());
		ui.displayLine(sb.toString());
	}
}
