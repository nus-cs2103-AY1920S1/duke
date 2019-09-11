package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;

/**
 * Represents the 'list' command
 */
public class ListCommand implements Command {
    /**
     * Lists the current tasks in the task list
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public String execute(Storage storage, Ui ui, TaskList tasks) {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            output = output + (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        return output;
    }
}
