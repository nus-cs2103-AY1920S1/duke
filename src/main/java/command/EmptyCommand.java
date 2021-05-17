package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command that does nothing.
 */
public class EmptyCommand extends Command {

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // do nothing
    }
}
