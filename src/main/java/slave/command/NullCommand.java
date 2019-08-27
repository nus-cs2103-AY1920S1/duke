package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;
import slave.exception.InvalidCommandException;

/**
 * A command representing null
 */
public class NullCommand extends Command {


    private String command;
    /**
     * Constructor for null command
     * @param command Command that is illegal / invalid
     */
    public NullCommand(String command) {
        this.commandType = CommandType.NULL;
        this.command = command;
    }

    /**
     * executes by throwing an exception which tells
     * the user that the input command is not valid
     * @param taskList list containing current tasks
     * @param ui user interface
     * @throws DukeException for invalid commands by user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        throw new InvalidCommandException(command);
    }
}
