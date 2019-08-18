package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a duke.command to add a new duke.task.
 */
public class AddCommand extends Command {
    private final Task t;

    /**
     * Creates a new AddCommand with the given duke.task.
     *
     * @param t The duke.task to add.
     */
    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes this duke.command on the given duke.task list and user interface.
     *
     * @param tl The duke.task list.
     * @param ui The user interface displaying events on the duke.task list.
     */
    public void execute(TaskList tl, Ui ui) {
        tl.add(t);
        ui.printMessage("Got it. I've added this duke.task:");
        ui.printMessage("  " + t);
        int n = tl.size();
        ui.printMessage(String.format("Now you have %d duke.task%s in the list.", n, n == 1 ? "" : "s"));
    }
}
