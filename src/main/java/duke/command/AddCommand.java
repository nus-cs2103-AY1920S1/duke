package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a command to add a new task.
 */
public class AddCommand extends Command {
    private final Task t;

    /**
     * Creates a new AddCommand with the given task.
     *
     * @param t The task to add.
     */
    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     */
    public void execute(TaskList tl, Ui ui) {
        tl.add(t);
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + t);
        int n = tl.size();
        ui.printMessage(String.format("Now you have %d task%s in the list.", n, n == 1 ? "" : "s"));
    }
}
