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
        ui.printBye();
        System.exit(0);
    }
}
