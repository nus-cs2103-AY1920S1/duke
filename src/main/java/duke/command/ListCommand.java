package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a <code>Command</code> to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList        {@inheritDoc}
     * @param ui              {@inheritDoc}
     * @param storage         {@inheritDoc}
     * @return                a string showing all tasks in the task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showFullList(taskList);
    }
}
