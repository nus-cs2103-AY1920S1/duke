/**
 * SearchCommand represents a user command to search for tasks that contains a particular word in
 * its description.
 */

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

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        HashMap<Integer, Task> searchResults = tasks.search(searchDescription);
        ui.showSearchResults(searchResults);
    }
}
