package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * A Command to exit the app.
 */
public class CommandExit extends Command {

    /**
     * Prints the exit message through the UI.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Returns true to break the app's main loop and exit.
     * @return True (boolean).
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
