package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

import kappa.exception.KappaException;

/**
 * Command that clears storage and taskList.
 */
public class ClearCommand extends Command {

    /**
     * Constructor for Clear Command.
     */
    public ClearCommand() {
        this.commandType = CommandType.CLEAR;
    }

    /**
     * Executes command by clearing list and storage and printing a clear message to the user.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException If list is not found.
     * @return String containing Kappa's formatted dialog.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws KappaException {
        tasks.clearList();
        return ui.printClearCommand();
    }
}
