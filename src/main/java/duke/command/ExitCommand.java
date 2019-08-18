package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to exit Duke.
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
     * Executes this command on the given task list and user interface.
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     * @param storage The place where tasks will be stored.
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }
}
