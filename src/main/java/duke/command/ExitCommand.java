package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a duke.command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Creates a new ExitCommand. In particular, sets the exit variable of its superclass
     * to true so the application can exit.
     */
    public ExitCommand() {
        exit = true;
    }

    /**
     * Executes this duke.command on the given duke.task list and user interface.
     *
     * @param tl The duke.task list.
     * @param ui The user interface displaying events on the duke.task list.
     */
    public void execute(TaskList tl, Ui ui) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }
}
