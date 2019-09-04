package command;

import run.Storage;
import run.TaskList;
import run.Ui;

/**
 * Extends Command class and lists (prints) out current tasks in TaskList to user
 */
public class ListCommand extends Command {

    /**
     * Retrives list of current tasks from TaskList and passes this to ui to iterate
     * through and print in the relevant format
     * @param tasks current TaskList with all current tasks
     * @param ui current user interface
     * @param storage current storage state
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    /**
     * Checks if this command is an exit ("bye") command
     * @return false boolean since command is not exit ("bye") command
     */
    public boolean isExit() {
        return false;
    }
}