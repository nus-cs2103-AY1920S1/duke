package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command instructing Duke to list out all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command and lists out all the tasks.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.getListOfTasks();
        return listOfTasks;
    }

    /**
     * Checks if this command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }
}
