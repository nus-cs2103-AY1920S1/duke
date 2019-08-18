package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends Command {
    private final int i;

    /**
     * Creates a new DeleteCommand with the specified index.
     *
     * @param s The index of the task to delete, where the first task is 1.
     */
    public DeleteCommand(String s) {
        try {
            this.i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task index for the delete command must be an integer.");
        }
    }

    /**
     * Executes this command on the given task list and user interface.
     * @param tl The task list.
     * @param ui The user interface displaying events on the task list.
     * @param storage The place where tasks will be stored.
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        try {
            Task t = tl.delete(i);
            ui.printMessage("Noted. I've removed this task:");
            ui.printMessage("  " + t);
            int n = tl.size();
            ui.printMessage(String.format("Now you have %d task%s in the list.", n, n == 1 ? "" : "s"));
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Task index must be between 1 and " + tl.size() + ".");
        }

        try {
            storage.write(tl.export());
        } catch (IOException e) {
            ui.printError("Error writing tasks to file");
        }
    }
}
