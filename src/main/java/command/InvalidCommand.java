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
     * Executes the given InvalidCommand task.
     * @param tasks A {@Code: TaskList} object
     * @param ui A {@Code: Ui} object
     * @param storage A {@Code: Storage} object
     * @return A String representing the error message of the command
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\t" + errorMessage;
        return output;

    }
}