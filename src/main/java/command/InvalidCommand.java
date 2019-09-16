package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class InvalidCommand extends Command {

    String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the given task and prints the respective output.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\t" + errorMessage;
        return output;

    }
}