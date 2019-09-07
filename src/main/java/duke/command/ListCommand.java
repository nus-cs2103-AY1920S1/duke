package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * The <code>ListCommand</code> is created when the user enters <code>"list"</code>. The list command will show the list
 * in a table form for the user when executed on the user interface.
 */
public class ListCommand implements Command {

    /**
     * Executes the command. This will display the list of tasks in the user interface.
     * @param tasks the list of tasks
     * @param commandLineUserInterface the user interface
     * @param storage the storage for the tasks
     */
    public String execute(TaskList tasks, UserInterface commandLineUserInterface, Storage storage) {
        return commandLineUserInterface.showTable(tasks.list());
    }

}
