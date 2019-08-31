package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Command class is an abstract class that other command classes inherit from.
 *
 * @author scwaterbear
 */
public abstract class Command {

    /**
     * Performs action determined in subclass.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Sends signal to exit Duke.
     *
     * @return boolean if Duke should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Writes changes to task list to file.
     *
     * @param tasks list of tasks.
     * @param storage storage.
     * @throws DukeException If error saving data to file.
     */
    void persistState(TaskList tasks, Storage storage) throws DukeException {
        storage.saveDataToFile(tasks.getAllTasks());
    }
}
