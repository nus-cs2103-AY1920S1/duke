package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import java.io.IOException;

/**
 * List command
 */
public class ListCommand extends Command {

    /**
     * Shows list of tasks
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(ui.padMessage(tasks.getTasks()));
    }

    /**
     * Does not exit
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}