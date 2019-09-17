package duke.commands;

import java.io.IOException;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;


/**
 * Represents a command which contains an execute method to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Initialises the exit command
     */
    public ExitCommand(){
        this.commandType = CommandType.EXIT;
    }

    /**
     * Prints the exit message.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printByeMessage();
    }

}

