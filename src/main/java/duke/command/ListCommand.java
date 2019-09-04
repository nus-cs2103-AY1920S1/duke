package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Class that represents the command to print out the list.
 */
public class ListCommand extends Command {

    /**
     * Constructor that takes in empty String to create the object.
     * @param message Usually is an empty string as it is not used.
     */
    public ListCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (listOfTasks.size() == 0) {
            throw new DukeException("     The list is empty!");
        }
        ui.printList();
    }
}
