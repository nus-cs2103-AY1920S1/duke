package duke.command;

import duke.error.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.Alias;

/**
 * Executes operation to print all contents in abbreviation list.
 */
public class ListAlias extends Command {

    /**
     * Executes operation to list all the tasks found in the alias list.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return Alias.showAliases();
    }

    public boolean isExit() {
        return false;
    }
}