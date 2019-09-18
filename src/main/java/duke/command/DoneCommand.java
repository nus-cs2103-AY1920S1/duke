package duke.command;

import duke.logic.TaskList;
import duke.logic.Storage;
import duke.task.Task;

/**
 * Represents a DoneCommand which sets Task to done in TaskList.
 */

public class DoneCommand extends Command {
	/**
	 * Represent position of Task in TaskList to set to done.
	 */
	private int position;

	/**
	 * Constructor of DoneCommand.
	 *
	 * @param position Sets position to set as done as input.
	 */
	public DoneCommand(int position) {
		super();
		this.position = position;
	}

	/**
	 * Executes done command. Sets Task from TaskList at given position to done.
	 *
	 * @param tasks   Sets Task in TaskList to done.
	 * @param storage Saves to Storage or loads from Storage if required.
	 * @return String representation of executed command.
	 */
	@Override
	public String execute(TaskList tasks, Storage storage) {
		Task task = tasks.markAsDone(this.position);
		StringBuilder sb = new StringBuilder();
		sb.append("Nice! I've marked this task as done:\n");
		sb.append("  " + task);
		return sb.toString();
	}
}
