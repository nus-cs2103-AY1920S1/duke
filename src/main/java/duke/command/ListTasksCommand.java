package duke.command;

import duke.history.History;
import duke.storage.Storage;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

/**
 * Represents a command to list the tasks in the task list.
 */
public class ListTasksCommand extends Command {
    /**
     * Lists all tasks in the task list.
     * @param tasks List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage, History history) {
        super.commandOutput = ui.showList(tasks);
    } // End method.
}
