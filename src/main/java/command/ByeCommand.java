package command;

import main.Storage;
import main.TaskList;
import main.UI;

/**
 * A Command to handle "bye" inputs resulting in a termination of the Duke program.
 */
public class ByeCommand implements Command {
    public ByeCommand() { }

    /**
     * Prints the bye message via the UI method, UI.bye().
     *
     * @param taskList The TaskList used to store the Tasks for this instance of Duke (not used in this method).
     * @param storage The Storage used to store the Tasks on the hard disk (not used in this method).
     */
    public String execute(TaskList taskList, Storage storage) {
        return UI.bye();
    }
}
