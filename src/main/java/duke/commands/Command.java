package duke.commands;

import duke.DukeException;
import duke.tasks.TaskList;
import duke.Storage;
import duke.ui.Ui;

public abstract class Command {
    /**
     * Perform a command.
     * @param taskList task list of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Check whether the command is an exit command.
     * @return whether the command is an exit command, default value is false
     */
    public boolean isExit() {
        return false;
    }
}
