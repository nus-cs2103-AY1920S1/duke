package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command that exits Duke.
 */

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewellMessage();
    }

    /**
     * Returns a Boolean to indicate whether this command is an ExitCommand.
     *
     * @return Whether this Command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
