package dukegui.command;

import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

/**
 * Represents the "list" command supported by Duke.
 */
public class ListCommand extends Command {

    /**
     * Returns the response to this ListCommand.
     *
     * @param taskList List of tasks to manage.
     * @param storage Storage to save any changes if necessary.
     */
    @Override
    public String getResponse(TaskList taskList, Storage storage) {
        return String.join("\n", taskList.listAll());
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
