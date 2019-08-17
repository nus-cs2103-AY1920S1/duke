package command;

import task.TaskList;
import textual.Ui;

/**
 * Class representing a command to mark an item in the task list as done.
 */
public class DoneCommand extends Command {
    private final int i;

    /**
     * Creates a new DoneCommand with the specified index.
     *
     * @param i The index of the task to mark as done, where the first task is 1.
     */
    public DoneCommand(int i) {
        this.i = i;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     */
    public void execute(TaskList tl, Ui ui) {
        try {
            tl.markDone(i);
            ui.printMessage("Nice! I've marked this task as done:");
            ui.printMessage("  " + tl.get(i));
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Task index must be between 1 and " + tl.size() + ".");
        }
    }
}
