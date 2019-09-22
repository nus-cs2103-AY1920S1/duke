package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class ListCommand extends Command {

    /**
     * Executes the given List task.
     * @param tasks A {@Code: TaskList} object
     * @param ui A {@Code: Ui} object
     * @param storage A {@Code: Storage} object
     * @return A String representing the list of tasks
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\tHere are the tasks in your list: ";
        output += ui.getTasksAsString(tasks.getTaskList());
        return output;
    }

}