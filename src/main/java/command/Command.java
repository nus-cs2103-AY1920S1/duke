package command;

import task.TaskList;
import duke.UserInterface;
import duke.Storage;

/**
 * Abstract Command class that specifies the skeleton of all commands.
 * Commands are actions that the user can request.
 */
public abstract class Command {
    //Only TRUE if commands = bye which indicates the end of session.
    boolean isExit = false;

    /**
     * Executes the command and runs the related logic flow.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Tasks storage: load & save.
     */
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage);


    /**
     * @return Indicates if the command is to exit or not.
     */
    public boolean isExit() {
        return isExit;
    }
}
