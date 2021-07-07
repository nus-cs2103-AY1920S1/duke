package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.commons.exceptions.commandexceptions.InvalidCommandException;
import seedu.duke.commons.exceptions.historyexceptions.InvalidClearCacheKeyException;
import seedu.duke.model.Duke;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that allows the client to clear the entire cache of version history.
 * Contains a method that executes commands by clearing the history of this Command's Duke instance.
 * This command CANNOT BE UNDONE.
 * Stores information on the Duke instance, so TaskListHistory of Duke is emptied.
 */
public class ClearCacheCommand extends Command {

    private Duke duke;
    private static boolean clearCommandRequested;
    private String userKey;
    private static final String requiredKey = "CoNfiRMtOclEaR";

    /** Creates an instance of ClearCacheCommand.
     * Stores to attributes of ClearCacheCommand relevant information required to create a ClearCacheCommand.
     * Attribute is a reference to instance of Duke for which to perform clear cache operation(s).
     * @param duke Duke instance for which to perform clear cache operation.
     * @param userKey Key that a client of this class inputs to confirm on whether or not the ClearCacheCommand
     *                should proceed.
     * @throws DukeException If initialisation of the Duke instance as an attribute is unsuccessful.
     */
    public ClearCacheCommand(Duke duke, String userKey) throws DukeException {
        this.duke = duke;
        this.userKey = userKey;
    }

    /** Executes a ClearCacheCommand object using information stored in instance attributes.
     * Begins a fresh TaskListHistory with a given TaskList.
     * Displays a message indicating successful clearing of cache after cache clearing process has been completed.
     * @param tasks TaskList to start the new TaskListHistory
     * @param ui Ui with methods that are called to display a message upon successful clearing of this Duke's
     *           task history.
     * @param storage Storage that contains saved String representation of the given TaskList.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "TaskList that is to be displayed is null.";
        try {
            if (!clearCommandRequested && userKey.equals("")) {
                ui.showClearCacheConfirmationRequest();
                clearCommandRequested = true;
            } else if (clearCommandRequested && userKey.equals(requiredKey)) {
                duke.resetTaskListHistory();
                clearCommandRequested = false;
                ui.showClearCacheConfirmation();
            } else if (clearCommandRequested) {
                clearCommandRequested = false;
                throw new InvalidClearCacheKeyException("Input key to clear cache is invalid. Clearing of cache is"
                        + " not initialised and the process has been aborted.");
            } else {
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }

    /**
     * Returns whether or not a user has requested for TaskListHistory to be cleared.
     * @return A boolean denoting if a user has requested for TaskListHistory to be cleared.
     */
    public static boolean getClearCommandRequested() {
        return clearCommandRequested;
    }

    /**
     * Sets whether or not a user has requested for TaskListHistory to be cleared.
     * @param clearCommandRequested A boolean denoting if a user has requested for TaskListHistory to be cleared.
     */
    public static void setClearCommandRequested(boolean clearCommandRequested) {
        ClearCacheCommand.clearCommandRequested = clearCommandRequested;
    }

    /**
     * Returns the required key the user needs to enter in order to confirm clearing of TaskListHistory.
     * @return String literal required for the user to key in to be able to confirm clearing of TaskListHistory.
     */
    public static String getRequiredKey() {
        return ClearCacheCommand.requiredKey;
    }

}
