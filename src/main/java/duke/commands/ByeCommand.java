package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Shows Bye message.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(ui.showByeMessage());
    }

    /**
     * Exits.
     *
     * @return true
     */
    public boolean isExit() {
        return true;
    }

}