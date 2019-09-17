package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * Create add commands.
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to list of tasks, gives feedback to user after execution.
     *
     * @param tasks contains the current list of tasks
     * @param ui to give feedback to the user
     */
    public void execute(TaskList tasks, Ui ui, Saved file) {
        tasks.add(this.task);
        ui.printDuke("Got it. I've added this task:\n  "
                 + "\t" + this.task + "\n"
                 + "\t Now you have " + tasks.size() + " tasks in the list.\n");
    }
}
