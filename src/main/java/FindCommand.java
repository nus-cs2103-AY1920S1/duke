package duke.command;

import duke.error.DukeException;
import duke.task.Task;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import java.util.ArrayList;

/**
 * provide commands to find certain task by keywords.
 * */
public class FindCommand extends Command {
    protected String command;

    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * main method to perform operation.
     * find there is matching results
     * if successful, print out list of matches
     * */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> results = tasks.searchTasks(command);
            if (results.isEmpty()) {
                throw new DukeException("     0 Matching Results found!");
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