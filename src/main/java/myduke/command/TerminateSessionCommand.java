package myduke.command;

import myduke.storage.StorageManager;
import myduke.ui.Ui;
import myduke.task.TaskList;

/**
 * Terminates the current session.
 */
public class TerminateSessionCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) {
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
