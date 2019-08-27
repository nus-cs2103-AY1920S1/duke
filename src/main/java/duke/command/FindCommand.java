package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

	String details;

	public FindCommand(String details) {
		super();
		this.details = details;
	}

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

	public boolean isExit() {
		return false;
	}

}
