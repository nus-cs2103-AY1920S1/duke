package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * Terminates the program.
 */

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        ui.exitProgram();
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
