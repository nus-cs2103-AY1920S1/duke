package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a <code>Command</code> that finds all tasks that contain a given key word.
 */
public class FindCommand extends Command {

	String details;

	/**
	 * Constructor for <code>FindCommand</code>.
	 */
	public FindCommand(String details) {
		super();
		this.details = details;
	}

	/**
	 * Finds all tasks in the <code>TaskList</code> with descriptions containing the provided key word.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ArrayList<Task> matchingTasks = new ArrayList<Task>();
		for (int i = 0; i < tasks.getSize(); i++) {
			Task currentTask = tasks.getTask(i);
			String taskDescription = currentTask.getDescription();
			if (taskDescription.contains(details)) {
				matchingTasks.add(currentTask);
			}
		}
		ui.printFindMessage(matchingTasks);
	}

	/**
	 * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
	 * @return False.
	 */
	public boolean isExit() {
		return false;
	}

}
