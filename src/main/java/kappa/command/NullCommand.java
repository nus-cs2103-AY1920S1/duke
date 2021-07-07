package kappa.command;

import kappa.elements.TaskList;
import kappa.elements.Ui;

import kappa.exception.KappaException;
import kappa.exception.InvalidCommandException;

/**
 * A command representing null.
 */
public class NullCommand extends Command {

    private String command;

    /**
     * Constructor for null command.
     *
     * @param command Command that is illegal or invalid.
     */
    public NullCommand(String command) {
        this.commandType = CommandType.NULL;
        this.command = command;
    }

    /**
     * Executes by throwing an exception which tells the user that the input command is not valid.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException For invalid commands by user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws KappaException {
        throw new InvalidCommandException(command);
    }
}
