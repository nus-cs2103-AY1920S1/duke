package command;

import parser.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * @return false to signify the program is not terminating
     */
    public boolean canEnd() {
        return false;
    }

    /**
     * Execute a command.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
