package duke.commands;

import duke.ui.UI;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import java.io.IOException;

/**
 * Delete command
 */
public class IllegalCommand extends Command {

    /**
     * Shows illegal command
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showIllegalCommandMessage();
    }

    /**
     * Does not exit
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}