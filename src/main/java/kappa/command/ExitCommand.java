package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

/**
 * A command which represents exiting the Slave program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for exit command.
     */
    public ExitCommand() {
        this.commandType = CommandType.EXIT;
    }

    /**
     * Executes by showing the goodbye message.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @return String containing Kappa's formatted dialog.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.showByeMessage();
    }

}

