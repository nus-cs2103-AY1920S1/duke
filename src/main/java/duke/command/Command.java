package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Instantiates a Command.
     *
     * @param isExit boolean to indicate ExitCommand
     */
    Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command.
     *
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     * @return response from ui
     * @throws DukeException if there are any wrong user input
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Gets the boolean value for exit command.
     *
     * @return the boolean value if the command is exit or not
     */
    public boolean isExit() {
        return isExit;
    }
}