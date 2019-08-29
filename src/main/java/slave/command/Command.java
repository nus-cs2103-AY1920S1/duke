package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

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
     * @param taskList List containing current tasks.
     * @param ui User interface.
     * @throws DukeException Throws exception with error message in case anything goes wrong.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;
}
