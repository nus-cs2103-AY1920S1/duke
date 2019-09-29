package q.command;

import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;

/**
 * Handles showing the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Shows the list of tasks.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String showing the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            return ui.showNoTasks();
        }
        return ui.showTaskList(tasks);
    }
}
