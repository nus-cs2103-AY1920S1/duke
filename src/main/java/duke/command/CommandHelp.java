package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Command to help the user learn what the app can do.
 */
public class CommandHelp extends Command {
    /**
     * Prints a list of the app's commands through the UI.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @return Duke's response to the Command as a String.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.getHelpMessage();
    }
}
