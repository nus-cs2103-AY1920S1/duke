package command;

import task.TaskList;
import textual.Ui;

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
     *
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     */
    public abstract void execute(TaskList tl, Ui ui);
}
