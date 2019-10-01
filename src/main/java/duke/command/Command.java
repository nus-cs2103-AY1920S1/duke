package duke.command;

import duke.exception.DukeException;
import duke.storage.DukeStorage;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;

/**
 * Base class for the other commands. Specifies the abstract method that is required to be implemented
 * by the children commands.
 */
public abstract class Command {

    /**
     * Executes what the command is suppose to do.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @param storage The main storage of the application.
     * @throws DukeException Occurs when parts of the command cannot be executed.
     */
    public abstract String execute(MyList taskList, DukeUserInterface ui, DukeStorage storage) throws DukeException;

    /**
     * Returns true when a exit command is called. Else, returns false.
     *
     * @return Boolean which states whether to exit the application.
     */
    public boolean isExit() {
        return false;
    }
}
