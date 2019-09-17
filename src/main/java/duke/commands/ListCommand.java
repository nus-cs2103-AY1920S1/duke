package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * List command.
 */
public class ListCommand extends Command {

    /**
     * Shows list of tasks.
     *
     * @param tasks   tasks
     * @param ui      ui
     * @param storage storage
     * @return All tasks
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return tasks.getTasks();
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