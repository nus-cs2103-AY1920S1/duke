package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import java.io.IOException;

/**
 * Command class is an abstract class that other command classes inherit from.
 *
 * @author scwaterbear
 */
public abstract class Command {

    /**
     * Performs action determined in subclass.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Writes changes to task list to file.
     *
     * @param tasks   list of tasks.
     * @param storage storage.
     */
    boolean persistState(TaskList tasks, Storage storage) {
        try {
            storage.saveDataToFile(tasks.getAllTasks());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
