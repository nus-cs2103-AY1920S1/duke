package duke.commands;

import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a command which contains an execute method to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Initialises the exit command
     */
    public ExitCommand(){
        super(CommandType.COMMAND_EXIT);
    }

    /**
     * Prints the exit message.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        assert ui != null;
        return ui.printByeMessage();
    }

}

