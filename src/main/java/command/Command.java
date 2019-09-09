package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Instructs the program that it is not terminating.
     *
     * @return false to signify the program is not terminating
     */
    public boolean canEnd() {
        return false;
    }

    /**
     * Executes a command.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
