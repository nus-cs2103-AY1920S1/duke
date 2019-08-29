package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

/**
 * Command that clears storage and taskList.
 */
public class ClearCommand extends Command {

    /**
     * Constructor.
     */
    public ClearCommand() {
        this.commandType = CommandType.CLEAR;
    }

    /**
     * Executes command by clearing list and storage and printing a clear message to the user.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws DukeException If list is not found.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        tasks.clearList();
        ui.printClearCommand();
    }
}
