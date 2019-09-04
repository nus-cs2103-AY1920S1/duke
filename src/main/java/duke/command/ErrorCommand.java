package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Class that represents that command to print an error message.
 */
public class ErrorCommand extends Command {

    /**
     * The UI needed to print the error message.
     */
    private UI errorPrinter;

    /**
     * Constructor that takes in the error message as String.
     * @param message The error message.
     */
    public ErrorCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        throw new DukeException(getMessage());
    }
}
