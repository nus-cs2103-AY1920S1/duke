package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class ExitCommand extends Command {

    /**
     * Executes the given task and prints the respective output.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\tBye. Hope to see you again soon!";
        return output;
    }

}