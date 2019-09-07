package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * This is an abstraction over the commands entered by the user. All commands that implements the <code>Command</code>
 * interface will have to override the <code>execute</code> method.
 */
public interface Command {

    /**
     * Executes the command.
     * @param tasks the list of tasks
     * @param commandLineUserInterface the user interface
     * @param storage the storage for the tasks
     */
    String execute(TaskList tasks, UserInterface commandLineUserInterface, Storage storage);

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     */
    default boolean isExit() {
        return this instanceof ExitCommand;
    }

}
