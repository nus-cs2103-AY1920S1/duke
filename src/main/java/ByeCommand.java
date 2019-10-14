package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represent a command to exit the program.
 */
public class ByeCommand implements Command {
    /**
     * Leaves the program upon execution.
     * 
     * @param tasks   a tasklist containing all the existing tasks
     * @param ui      a ui which will output the message
     * @param storage a storage object which will write to the file
     * @return a string with the message when the program finishes
     * @throws DukeException throw a DukeException if it faces any issues
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.byeMessage();
    }

    /**
     * Method to verify if it is an exit command.
     * 
     * @return a boolean value that returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
