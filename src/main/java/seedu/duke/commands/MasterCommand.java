package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TriviaException;
import seedu.duke.trivia.Trivia;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

public abstract class MasterCommand {

    /**
     * Default execute for debugging purposes.
     *
     * @return null if children not implemented properly.
     */
    public String execute() {
        return null;
    };

    /**
     * Base for base duke commands.
     *
     * @param taskList TaskList of tasks to be operated on.
     * @param ui UI to display messages to the user.
     * @param storageHandler Storage to write or read files if applicable.
     * @return String that the UI specifies.
     * @throws DukeException Throws if reading or writing files fails wherever storage is involved.
     */
    public String execute(TaskList taskList, UI ui, Storage storageHandler) throws DukeException {
        return null;
    };

    /**
     * Executes the command as defined in the children classes.
     *
     * @param trivia Trivia object that is used for the trivia.
     * @param ui UI to display messages to the user.
     * @param storage Storage to write or read files if applicable.
     * @return String that displays messages to the user.
     * @throws TriviaException Throws exceptions defined in the children classes.
     */
    public String execute(Trivia trivia, UI ui, Storage storage) throws TriviaException {
        return null;
    }

    /**
     * Determines if the command will result in the end of the application.
     *
     * @return Boolean to determine if the command will end the application or not. By default set to 0;
     */
    public boolean isExit() {
        return false;
    };
}
