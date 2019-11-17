package duke.command;

import duke.exception.InvalidParameterException;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.UserInterface;

/**
 * This is an abstraction over the commands entered by the user. All commands that implements the <code>Command</code>
 * interface will have to override the <code>execute</code> method.
 */
public interface Command {

    /**
     * Executes the command.
     * @param taskManager the task manager for the tasks
     * @param ui the user interface
     * @param storage the storage for the taskManager
     * @return a string representation of the output after command execution
     * @throws InvalidParameterException if the parameters passed into the command are invalid
     */
    String execute(TaskManager taskManager, UserInterface ui, Storage storage) throws InvalidParameterException;

}
