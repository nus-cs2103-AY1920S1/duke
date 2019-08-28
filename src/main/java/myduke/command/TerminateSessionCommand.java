package myduke.command;

import myduke.core.StorageManager;
import myduke.core.Ui;
import myduke.exception.DukeException;
import myduke.task.TaskList;

/**
 * Terminates the current session.
 */
public class TerminateSessionCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
