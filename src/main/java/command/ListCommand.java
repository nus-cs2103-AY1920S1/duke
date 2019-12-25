package command;

import run.Storage;
import run.TaskList;
import run.Ui;

/**
 * Extends Command class and lists (prints) out current tasks in TaskList to user.
 */
public class ListCommand extends Command {

    /**
     * Retrieves list of current tasks from TaskList and passes this to ui to iterate
     * through and print in the relevant format.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }

}