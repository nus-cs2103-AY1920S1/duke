package command;

import utils.Storage;
import utils.TaskList;
import utils.Ui;

public class ExitCommand extends Command {

    /**
     * Executes the given exit task.
     * @param tasks A {@Code: TaskList} object
     * @param ui A {@Code: Ui} object
     * @param storage A {@Code: Storage} object
     * @return A String representing the information of the executed task
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String output = "";

        // Save output as String
        output += "\n\tBye. Hope to see you again soon!";
        return output;
    }

}