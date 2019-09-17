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
     * @return bye message
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.getByeMessage();
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