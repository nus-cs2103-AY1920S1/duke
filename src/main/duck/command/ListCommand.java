package duck.command;

import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

/**
 * This is a <code>Command</code> to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in the task list.
     *
     * @param taskList        the task list that provides information about users' current tasks and to be modified
     * @param ui              the <code>Ui</code> object to handle input and output
     * @param storage         the <code>Storage</code> object to load and record data
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFullList(taskList);
    }
}
