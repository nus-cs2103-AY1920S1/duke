package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

/**
 * A Command to search the TaskList with a specific query.
 */
public class CommandFind extends Command {
    private TaskList results;
    private String query;

    /**
     * Instantiates a new 'Find' Command with a given query.
     * @param query The user's search query as a String.
     */
    public CommandFind(String query) {
        this.results = new TaskList();
        this.query = query;
    }

    /**
     * Searches the TaskList with the user's query and prints the results.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If the search fails (error in search method).
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.toString().contains(query)) {
                    results.add(task);
                }
            }
        } catch (Exception e) {
            throw new DukeException("Search of task list failed.");
        }

        return ui.getSearchResults(results);
    }
}
