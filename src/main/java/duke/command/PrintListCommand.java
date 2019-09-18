package duke.command;

import duke.logic.TaskList;
import duke.logic.Storage;

/**
 * Represents a PrintListCommand which prints all Tasks in the TaskList.
 */
public class PrintListCommand extends Command {
	/**
	 * Constructor of PrintListCommand.
	 */
	public PrintListCommand() {
		super();
	}

	/**
	 * Execute print list command. Prints all Tasks in the TaskList.
	 *
	 * @param tasks   Print the Tasks in TaskList.
	 * @param storage Saves to Storage or loads from Storage if required.
	 * @return String representation of executed command.
	 */
	@Override
	public String execute(TaskList tasks, Storage storage) {
		StringBuilder sb = new StringBuilder();
		sb.append("Here are the tasks in your list:\n");
		for (int i = 1; i < tasks.size() + 1; i++) {
			sb.append(String.format("  %d. %s\n", i, tasks.get(i)));
		}
		return sb.toString();
	}

	/**
	 * Returns true if PrintCommand is of same instance as object. Used for testing.
	 *
	 * @param object Object to compare equality with.
	 * @return Boolean value of whether current PrintCommand equals input Object.
	 */
	@Override
	public boolean equals(Object object) {
		return (object instanceof PrintListCommand);
	}
}
