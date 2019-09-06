package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidInputException;

import java.io.IOException;

/**
 * FindCommand class which extends the abstract class Command.
 * This class handles the finding of tasks, and subsequent updating
 * the txt file.
 */
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
