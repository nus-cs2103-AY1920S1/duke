package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;

import java.io.IOException;


public class FindCommand extends Command {
	public String command;
	
	public FindCommand(String command) {
		this.command = command;
	}
	
	public String execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, InvalidInputException,
		InvalidDescriptionException, IOException {
		return tasks.findTask(this.command);
	}
	
	public boolean isExit() {
		return false;
	}
	
}
