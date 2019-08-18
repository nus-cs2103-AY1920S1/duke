package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a duke.command to mark an item in the duke.task list as done.
 */
public class DoneCommand extends Command {
    private final int i;

    /**
     * Creates a new DoneCommand with the specified index.
     *
     * @param s The index of the duke.task to mark as done, where the first duke.task is 1.
     */
    public DoneCommand(String s) {
        try {
            this.i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task index for the done duke.command must be an integer.");
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
            tl.markDone(i);
            ui.printMessage("Nice! I've marked this duke.task as done:");
            ui.printMessage("  " + tl.get(i));
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Task index must be between 1 and " + tl.size() + ".");
        }
    }
}
