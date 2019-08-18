package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a duke.command to delete an item from the duke.task list.
 */
public class DeleteCommand extends Command {
    private final int i;

    /**
     * Creates a new DeleteCommand with the specified index.
     *
     * @param s The index of the duke.task to delete, where the first duke.task is 1.
     */
    public DeleteCommand(String s) {
        try {
            this.i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task index for the delete duke.command must be an integer.");
        }
    }

    /**
     * Executes this duke.command on the given duke.task list and user interface.
     *
     * @param tl The duke.task list.
     * @param ui The user interface displaying events on the duke.task list.
     */
    public void execute(TaskList tl, Ui ui) {
        try {
            Task t = tl.delete(i);
            ui.printMessage("Noted. I've removed this duke.task:");
            ui.printMessage("  " + t);
            int n = tl.size();
            ui.printMessage(String.format("Now you have %d duke.task%s in the list.", n, n == 1 ? "" : "s"));
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Task index must be between 1 and " + tl.size() + ".");
        }
    }
}
