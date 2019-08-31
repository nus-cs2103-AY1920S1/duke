package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract class representing individual commands.
 */
public abstract class Command {
    boolean exit = false;

    /**
     * Determines whether this command is exiting, i.e. whether the program should exit
     * after executing this command.
     *
     * @return Whether this command is exiting.
     */
    public boolean isExit() {
        return exit;
    }
    
    /**
     * Executes this command on the given task list and user interface.
     * Returns the response to this input, which is seen by the user.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     * @return The response to the user's command.
     */
    public abstract String execute(TaskList tl, Storage storage);
}
