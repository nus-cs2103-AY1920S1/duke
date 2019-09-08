package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find the tasks containing a given keyword in the task list.
 */
public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Find the tasks containing a given keyword in the task list.
     * @param list List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    @Override
    public void execute(Tasklist list, Ui ui, Storage storage, History history) throws DukeException {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : list.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchList.add(task);
            } // End if.
        } // End for loop.
        super.commandOutput = ui.listFindMatches(matchList);
    }
}
