package duke.command;

import duke.error.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Executes operation to print all contents in TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes operation to list all the tasks found in the TaskList.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}