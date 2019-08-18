package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Abstract class representing individual commands.
 */
public abstract class Command {
    boolean exit = false;

    /**
     * Determines whether this duke.command is exiting, i.e. whether the program should exit
     * after executing this duke.command.
     *
     * @return Whether this duke.command is exiting.
     */
    public boolean isExit() {
        return exit;
    }
    
    /**
     * Executes this duke.command on the given duke.task list and user interface.
     *
     * @param tl The duke.task list.
     * @param ui The user interface displaying events on the duke.task list.
     */
    public abstract void execute(TaskList tl, Ui ui);
}
