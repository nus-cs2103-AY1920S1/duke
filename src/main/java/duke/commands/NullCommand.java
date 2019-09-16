package duke.commands;

import java.io.IOException;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;
import duke.errors.DukeExceptionType;

/**
 * Represents a command that is invalid
 */
public class NullCommand extends Command{

    /**
     * Initialises the null command
     */
    public NullCommand(){
        this.commandType = CommandType.NULL;
    }

    /**
     * Throws an exception which tells
     * the user that the input command is not valid
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @throws DukeException Thrown when the command does not exist
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        throw new DukeException("Invalid Command! Please try again.", DukeExceptionType.INVALIDCOMMAND);
    }

}
