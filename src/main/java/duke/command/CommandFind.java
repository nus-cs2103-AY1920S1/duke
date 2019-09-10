package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.History;
import duke.util.Storage;
import duke.util.Ui;

import java.util.concurrent.ExecutionException;

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
     * Searches the TaskList with the user's query and returns the results as a String.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If the search fails (error in search method).
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException {
        try {
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.toString().contains(query)) {
                    results.add(task);
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("Search of task list failed.");
        }

        return ui.getSearchResults(results);
    }
}
