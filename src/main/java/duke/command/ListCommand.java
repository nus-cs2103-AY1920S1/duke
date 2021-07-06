package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a command instructing Duke to list out all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command and lists out all the tasks.
     * @param tasks List of tasks
     * @param storage Storage object
     * @return Duke's response.
     */
    public String execute(TaskList tasks, Storage storage) {
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
