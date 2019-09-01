package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;
import slave.exception.InvalidCommandException;

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
     * @throws DukeException For invalid commands by user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        throw new InvalidCommandException(command);
    }
}
