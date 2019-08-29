package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Terminates the program.
 */

public class ExitCommand extends Command {

    /**
     * Executes exit program on user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface displaying program exiting.
     * @param storage local storage of data.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        ui.exitProgram();
    }

    /**
     * @return true as program has terminated upon program exiting.
     */
    @Override
    public boolean isTerminated() {
        return true;
    }
}
