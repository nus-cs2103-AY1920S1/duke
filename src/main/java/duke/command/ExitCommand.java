package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

	public ExitCommand() {
		super();
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.printExitMessage();
		ui.closeScanner();
	}

	public boolean isExit() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof ExitCommand) {
			return true;
		} else {
			return false;
		}
	}

}
