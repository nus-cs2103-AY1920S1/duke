package trackr.command;

import trackr.storage.Storage;
import trackr.tasklist.TaskList;
import trackr.ui.Ui;

/**
 * Class when user issues an Exit command.
 */
public class ExitCommand extends Command {

    /**
     * Calls the ui to execute actions for an exit command.
     * @param tasks List of tasks
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
