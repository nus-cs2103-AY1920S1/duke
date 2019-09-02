package myduke.command;

import myduke.storage.StorageManager;
import myduke.ui.Ui;
import myduke.exception.DukeException;
import myduke.task.TaskList;

/**
 * An Abstract class of commands that can be performed on a list of task.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param ui       UI.
     * @param storage  Storage.
     * @throws DukeException If a checked error occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException;

    /**
     * Indicates if the program should exit.
     *
     * @return A boolean indicating whether the exit condition has been met.
     */
    public boolean shouldExit() {
        return false;
    }
}
