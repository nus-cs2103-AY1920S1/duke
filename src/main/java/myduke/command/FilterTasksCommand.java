package myduke.command;

import myduke.storage.StorageManager;
import myduke.ui.Ui;
import myduke.exception.DukeException;
import myduke.task.TaskList;

/**
 * Filters tasks based on a search term.
 */
public class FilterTasksCommand extends Command {
    private final String keyword;

    public FilterTasksCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
        TaskList filteredTaskList = taskList.filterTasks(this.keyword);
        ui.printResponse("Here are the matching tasks in your list:", filteredTaskList);
    }

}
