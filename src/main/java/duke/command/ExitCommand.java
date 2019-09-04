package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class when user issues an Exit command.
 */
public class ExitCommand extends Command {

    /**
     * Returns true indicating that this is an exit command.
     * @return boolean true since this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Calls the ui to execute actions for an exit command.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.handleBye();
    }
}
