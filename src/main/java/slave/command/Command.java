package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;

import slave.exception.DukeException;

/**
 * abstract class Command that represents an action by the user
 */
public abstract class Command {

    CommandType commandType;

    /**
     * getter for type of command
     * @return type of command
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     *
     * @param taskList list containing current tasks
     * @param ui user interface
     * @throws DukeException throws exception with error message in case anything goes wrong
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;
}
