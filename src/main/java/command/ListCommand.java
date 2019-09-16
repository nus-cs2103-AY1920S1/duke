package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class ListCommand extends Command {

    /**
     * Executes the given task and prints the respective output.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\tHere are the tasks in your list: ";
        output += ui.getTasksAsString(tasks.getTaskList());
        return output;
    }

}