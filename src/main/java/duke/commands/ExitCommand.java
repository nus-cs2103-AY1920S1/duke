/**
 * This class represents a specific command of exiting from Duke.
 */

package duke.commands;

import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    /**
     * Prints the exit line from Duke and closes the chat bot.
     *
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printLine("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }

}
