package duke.command;

import duke.logic.TaskList;
import duke.logic.Storage;

import duke.task.Task;

/**
 * Represents an AddCommand in Duke which adds Tasks to the TaskList
 */

public class AddCommand extends Command {
	/**
	 * Represents the task to add.
	 */
	Task task;

	/**
	 * Constructor for add command.
	 *
	 * @param task Sets task to add as input.
	 */
	public AddCommand(Task task) {
		super();
		this.task = task;
	}

	/**
	 * Executes add command. Adds task to TaskList and prints out action.
	 *
	 * @param tasks   Adds task to tasks.
	 * @param storage Saves to Storage or loads from Storage if required.
	 * @return String representation of executed command.
	 */
	public String execute(TaskList tasks, Storage storage) {
		tasks.add(this.task);
		StringBuilder sb = new StringBuilder();
		sb.append("Got it. I've added this task:\n");
		sb.append(String.format("  %s\n", this.task));
		sb.append(String.format("Now you have %d tasks in the list.", tasks.size()));
		return sb.toString();
	}
}
