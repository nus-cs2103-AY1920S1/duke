package q.command;

import q.error.QException;
import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Abstract form of the command based on a user's input.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String describing the execution.
     * @throws QException When an error in execution occurs.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws QException;

    public boolean isExit() {
        return false;
    }
}
