package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;

public abstract class Command {
    String commandInformation;

	public Command() { }

	public Command(String commandInformation) {
		this.commandInformation = commandInformation;
	}

	public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

	public abstract boolean isExit();


}
