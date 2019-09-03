package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
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
        if (fullCommand.length == 1) {
            throw new DukeException("     OOPS!!! Please specify a search term.");
        }
        return new FindCommand(fullCommand[1]);
    }
    /**
     * Executes the search command.
     *
     * @param tasks Task list to search from.
     * @param ui User interface that assists with printing.
     * @param storage Unused.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(searchTerm, ui);
    }
}
