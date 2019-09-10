package duke.command;

import duke.task.TaskList;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A Command to exit the app.
 */
public class CommandExit extends Command {

    /**
     * Returns the exit message to be printed through the UI.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) {
        return Ui.getExitMessage();
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
