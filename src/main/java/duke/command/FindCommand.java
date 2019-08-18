package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

/**
 * Class representing a command to find items in the task list matching some keyword,
 * and to display those items.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword that is being searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     * @param storage The place where tasks will be stored.
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.printMessage("Here are the matching tasks in your list:");
        ArrayList<Task> filteredTasks = tl.filter(keyword);
        for (int i = 1; i <= filteredTasks.size(); i++) {
            ui.printMessage(i + ". " + filteredTasks.get(i - 1));
        }
    }
}
