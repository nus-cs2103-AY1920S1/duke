package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Command to search for tasks containing a specific key word/phrase and display those matching tasks.
 */
public class FindCommand extends Command {

    private String searchTerm;

    /**
     * Creates a FindCommand object with the specific key word/phrase assigned.
     *
     * @param searchTerm Key word/phrase to search for.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Parses the command given to Duke and creates a FindCommand if possible.
     *
     * @param fullCommand Full command split by the first whitespace.
     * @return FindCommand object to be created.
     * @throws DukeException If no search term has been specified.
     */
    public static FindCommand process(String[] fullCommand) throws DukeException {
        try {
            return new FindCommand(fullCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Please specify a search term.");
        }
    }

    /**
     * Executes the search command.
     *
     * @param tasks Task list to search from.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = tasks.findTasks(searchTerm);
        assert(!response.isEmpty());
        return response;
    }
}
