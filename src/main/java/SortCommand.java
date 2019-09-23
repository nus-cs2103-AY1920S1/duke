import java.util.Scanner;

/**
 * Represents the sort task functionality of duke
 */
public class SortCommand extends Command {

	/**
	 * Constructor of Sort Command
	 * @param inFullCommandScanner scanner to scan the user's desired task index to delete
	 */
	public SortCommand(Scanner inFullCommandScanner) {
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
			throw new DukeException("no sort type provided");
		}
		String sortType = inFullCommandScanner.next();
		assert (sortType != null);
		assert (!sortType.isEmpty());
		if (sortType.equals("date")) {
			tasks.sortByDate();
			ui.displayLine("successfully sorted task list by date");
		} else if (sortType.equals("name")) {
			tasks.sortByName();
			ui.displayLine("successfully sorted task list by name");
		} else {
			throw new DukeException("sort type not found");
		}
	}
}
