package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Class representing a command to mark an item in the task list as done.
 */
public class DoneCommand extends Command {
    private final int i;

    /**
     * Creates a new DoneCommand with the specified index.
     *
     * @param s The index of the task to mark as done, where the first task is 1.
     */
    public DoneCommand(String s) {
        try {
            this.i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task index for the done command must be an integer.");
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
            tl.markDone(i);
            ui.printMessage("Nice! I've marked this task as done:");
            ui.printMessage("  " + tl.get(i));
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
