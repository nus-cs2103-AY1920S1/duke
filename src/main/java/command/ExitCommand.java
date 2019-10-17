package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Represents the command to end the program.
 */
public class ExitCommand extends Command {

    /**
     * Prints the termination message and ends the program.
     *
     * @param task    The working TaskList object.
     * @param ui      The working Ui object.
     * @param storage The working storage object.
     */

    public String execute(TaskList task, Ui ui, Storage storage) {
        return ui.printBye();
    }
}

