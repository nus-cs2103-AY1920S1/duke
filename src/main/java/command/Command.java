package command;

import task.TaskList;
import textual.Ui;

/**
 * Abstract class representing individual commands.
 */
public abstract class Command {
    boolean exit = false;
    
    public boolean isExit() {
        return exit;
    }
    
    /**
     * Executes this command on the given task list, using the user interface
     * to display follow-up prompts to the user.
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     */
    public abstract void execute(TaskList tl, Ui ui);
}
