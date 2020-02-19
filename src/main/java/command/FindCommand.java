package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class FindCommand extends Command {

    String description;

    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the given find task.
     * @param tasks A {@Code: TaskList} object
     * @param ui A {@Code: Ui} object
     * @param storage A {@Code: Storage} object
     * @return A String representing the information of the executed task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\tHere are the matching tasks in your list: ";
        output += ui.getTasksAsString(tasks.findTasks(description));
        return output;
    }
}