package duke.command;

import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

/**
 * The <code>ListCommand</code> is created when the user enters <code>"list"</code>. The list command will show the list
 * in a table form for the user when executed on the user interface.
 */
public class ListCommand implements Command {

    /**
     * Executes the command. This will display the list of tasks in the user interface.
     * @param taskManager the task manager for the tasks
     * @param ui the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskManager taskManager, UserInterface ui, Storage storage) {
        return ui.showTable(taskManager.showTaskList());
    }

}
