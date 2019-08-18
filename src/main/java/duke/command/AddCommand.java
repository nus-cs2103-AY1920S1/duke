package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;

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
     * @param storage The place where tasks will be stored.
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        tl.add(t);
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + t);
        int n = tl.size();
        ui.printMessage(String.format("Now you have %d task%s in the list.", n, n == 1 ? "" : "s"));
        
        try {
            storage.write(tl.export());
        } catch (IOException e) {
            ui.printError("Error writing tasks to file");
        }
    }
}
