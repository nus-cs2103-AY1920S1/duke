package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

/**
 * Class that represent the command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructor that takes in empty string to create the object.
     * @param message Usually is an empty string.
     */
    public ByeCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {

    }

    @Override
    public String toString() {
        return "See you again later! Press ENTER to leave the program";
    }
}
