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
     * Executes the given task and prints the respective output.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\tHere are the matching tasks in your list: ";
        output += ui.getTasksAsString(tasks.findTasks(description));
        return output;
    }
}