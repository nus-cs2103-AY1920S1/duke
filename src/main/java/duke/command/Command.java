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
     * @param taskManager the list of taskManager
     * @param ui the user interface
     * @param storage the storage for the taskManager
     */
    String execute(TaskManager taskManager, UserInterface ui, Storage storage) throws InvalidParameterException;

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     */
    default boolean isExit() {
        return this instanceof ExitCommand;
    }

}
