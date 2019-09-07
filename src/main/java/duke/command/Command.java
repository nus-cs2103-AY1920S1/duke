package duke.command;

import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * Commands are executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks the command should act on.
     * @param ui      The ui class.
     * @param storage The Storage that the command should act on.
     * @return The output of the command as a String.
     * @throws Exception If command fails to execute to completion.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if the command should cause the Duke program to exit.
     *
     * @return true if command should cause the Duke program to exit, otherwise returns false.
     */
    public boolean isExit() {
        return false;
    }
}
