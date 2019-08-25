package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

	public ListCommand() {
		super();
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printMessage("\t Here are the tasks in your list:");
		for (int i = 1; i <= tasks.getSize(); i++) {
			ui.printMessage("\t " + i + ". " + tasks.getTask(i - 1));
		}
	}

	public boolean isExit() {
		return false;
	}

}
