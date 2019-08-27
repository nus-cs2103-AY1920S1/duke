package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

/**
 * Bye command
 */
public class ByeCommand extends Command {

    /**
     * Shows Bye message
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showByeMessage();
    }

    /**
     * Exits!!
     * @return true
     */
    public boolean isExit() {
        return true;
    }

}