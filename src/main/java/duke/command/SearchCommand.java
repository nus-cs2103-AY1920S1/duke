package duke.command;

/**
 * SearchCommand represents a user command to search for tasks that contains a particular word in
 * its description.
 */

import duke.task.TaskList;
import duke.task.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.HashMap;

public class SearchCommand extends Command {
    private String searchDescription;

    /**
     * Constructor for SearchCommand.
     * @param searchDescription keyword user entered to find a task
     */
    public SearchCommand(String searchDescription) {
        super();
        isExit = false;
        this.searchDescription = searchDescription;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        HashMap<Integer, Task> searchResults = tasks.search(searchDescription);
        return ui.showSearchResults(searchResults);
    }
}
