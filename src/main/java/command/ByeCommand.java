package command;

import run.Storage;
import run.TaskList;
import run.Ui;

/**
 * Extends Command class and is used to exit the application/created when when user enters "bye".
 */
public class ByeCommand extends Command {

    /**
     * Does not manipulate the TaskList or storage in any way, but uses show exit
     * procedure to the user.
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     * @return String output of executed command to be shown to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }

}