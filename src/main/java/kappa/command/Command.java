package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

import kappa.exception.KappaException;

/**
 * Abstract class Command that represents an action by the user.
 */
public abstract class Command {

    CommandType commandType;

    /**
     * Gets type of command.
     *
     * @return Type of command.
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Abstract method execute which are actions the command should do.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException Throws exception with error message in case anything goes wrong.
     */
    public abstract String execute(TaskList tasks, Ui ui) throws KappaException;
}
