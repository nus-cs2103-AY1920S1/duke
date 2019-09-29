package seedu.duke.commands.base;

import seedu.duke.commands.MasterCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

/**
 * Abstract class to serve as the parent class for all base duke commands.
 */
public abstract class Command extends MasterCommand {

    /**
     * Executes the command as defined in the children classes.
     *
     * @param tasks TaskList of tasks to be operated on.
     * @param ui UI to display messages to the user.
     * @param storage Storage to write or read files if applicable.
     * @return String that the UI specifies.
     * @throws DukeException Throws if reading or writing files fails wherever storage is involved.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    /**
     * Determines if the command will result in the end of the application.
     *
     * @return Boolean to determine if the command will end the application or not. By default set to 0;
     */
    public boolean isExit() {
        return false;
    }

}
