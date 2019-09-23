package command;

import error.DukeException;
import task.Task;
import utils.Storage;
import utils.Ui;
import utils.TaskList;
import java.util.ArrayList;

/**
 * Deals with operation to find tasks that matches with user's input.
 */
public class FindCommand extends Command {
    protected String command;

    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Executes operation to find matching tasks with given input.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> results = tasks.searchTasks(command);
            if (results.isEmpty()) {
                throw new DukeException("0 Matching Results found!");
            } else {
                return ui.printMatches(results);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}