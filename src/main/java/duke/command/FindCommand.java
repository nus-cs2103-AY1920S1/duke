package duke.command;

import duke.logic.TaskList;
import duke.logic.Storage;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents FindCommand which searches tasks in Duke.
 */
public class FindCommand extends Command {
	/**
	 * Represents string occurrence that FindCommand searches for.
	 */
	String toFind;

	/**
	 * Constructor of FindCommand.
	 *
	 * @param toFind Sets string to search for as input.
	 */
	public FindCommand(String toFind) {
		this.toFind = toFind.trim();
	}

	/**
	 * Executes FindCommand. Searches string occurrence in TaskList and returns results.
	 *
	 * @param tasks   Performs actions on TaskList if required.
	 * @param storage Saves to Storage or loads from Storage if required.
	 * @return String representation of executed command.
	 */
	@Override
	public String execute(TaskList tasks, Storage storage) {
		List<Task> matchingTasks = new ArrayList<>();
		List<Task> searchingList = tasks.getList();
		for (Task task : searchingList) {
			if (task.getName().contains(this.toFind)) {
				matchingTasks.add(task);
			}
		}

		if (matchingTasks.isEmpty()) {
			return ("No matching tasks found.");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Here are the matching tasks in your list:\n");
			for (int i = 0; i < matchingTasks.size(); i++) {
				sb.append(String.format("  %d.%s\n", i + 1, matchingTasks.get(i)));
			}
			return sb.toString();
		}
	}
}
