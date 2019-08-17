package command;

import task.Task;
import task.TaskList;
import textual.Ui;

/**
 * Class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends Command {
    private final int i;

    /**
     * Creates a new DeleteCommand with the specified index.
     *
     * @param i The index of the task to delete, where the first task is 1.
     */
    public DeleteCommand(int i) {
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
            Task t = tl.delete(i);
            ui.printMessage("Noted. I've removed this task:");
            ui.printMessage("  " + t);
            ui.printNumTasks(tl);
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Task index must be between 1 and " + tl.size() + ".");
        }
    }
}
