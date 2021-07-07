package myduke.command;

import myduke.storage.StorageManager;
import myduke.task.TaskList;
import myduke.ui.Ui;

/**
 * Initialises the current session.
 */
public class InitialiseDukeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) {
        storage.tryLoadFromDataBase();
        ui.displayMessage();
        ui.printResponse("Hello! I'm Duke");
        ui.printResponse("What can I do for you?");
    }
}
