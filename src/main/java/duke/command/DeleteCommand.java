package duke.command;

import duke.TaskList;
import duke.Storage;

import duke.task.Task;

/**
 * Represents a DeleteCommand which deletes Tasks from the TaskList.
 */
public class DeleteCommand extends Command {
	/**
	 * Represents position of Task to delete in TaskList.
	 */
	private int position;

	/**
	 * Constructor for DeleteCommand.
	 *
	 * @param position Sets position to delete as input.
	 */
	public DeleteCommand(int position) {
		super();
		this.position = position;
	}

	/**
	 * Executes delete command. Remove Task from TaskList at given position and prints out action.
	 *
	 * @param tasks   Removes Task from TaskList.
	 * @param storage Saves to Storage or loads from Storage if required.
	 * @return String representation of executed command.
	 */
	public String execute(TaskList tasks, Storage storage) {
		Task deleted = tasks.remove(position);
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Noted. I've removed this task: %s\n", deleted));
		sb.append(String.format("Now you have %d tasks in the list.", tasks.size()));
		return sb.toString();
	}

	/**
	 * Accessor for position in TaskList DeleteCommand will delete from.
	 *
	 * @return Returns position to delete from.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Returns true if DeleteCommand has same position as object. Used for testing.
	 *
	 * @param object Object to compare equality with.
	 * @return Boolean value of whether current DeleteCommand equals input Object.
	 */
	@Override
	public boolean equals(Object object) {
		return (object instanceof DeleteCommand) && (((DeleteCommand) object).getPosition() == this.position);
	}
}
