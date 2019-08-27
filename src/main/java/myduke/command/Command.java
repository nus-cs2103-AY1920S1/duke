package myduke.command;

import myduke.core.StorageManager;
import myduke.core.Ui;
import myduke.exception.DukeException;
import myduke.task.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     * @param taskList Task list.
     * @param ui       UI.
     * @param storage  Storage.
     * @throws DukeException If a checked error occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException;

    /**
     * Indicates if the program should exit.
     * @return {@code true} if exit condition is met, otherwise {@code false}.
     */
    public boolean shouldExit() {
        return false;
    }
}
