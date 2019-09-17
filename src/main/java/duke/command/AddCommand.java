package duke.command;

import duke.Saved;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Saved file) {
        tasks.add(this.task);
        ui.printDuke("Got it. I've added this task:\n  "
                 + "\t" + this.task + "\n"
                 + "\t Now you have " + tasks.size() + " tasks in the list.\n");
    }
}
