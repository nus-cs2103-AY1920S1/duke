package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a find Command.
 */
public class FindCommand extends Command {

    private String taskToBeFound;

    /**
     * Constructs a find Command.
     * @param taskToBeFound search item
     */
    public FindCommand(String taskToBeFound) {
        super(false);
        this.taskToBeFound = taskToBeFound;
    }

    /**
     * Executes the find command.
     * @param taskList task list for the command to operate on
     * @param ui ui object to print messages according to the command
     * @param storage storage for the task list to be written
     * @throws DukeException if task is not found
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList searchResults = taskList.find(taskToBeFound);
        ui.showSearchResults(searchResults);
    }
}
