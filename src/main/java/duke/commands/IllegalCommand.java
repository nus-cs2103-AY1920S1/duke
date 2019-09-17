package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Illegal command.
 */
public class IllegalCommand extends Command {

    /**
     * Shows illegal command.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     * @return Illegal Command Message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.getIllegalCommandMessage();
    }

    /**
     * Does not exit.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

}