package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    // Constructor
    private AddCommand(Task task) {
        this.task = task;
    }

    public static Command createAddCommand(Task task) {
        return new AddCommand(task);
    } // End method.

    @Override
    public void execute(Tasklist tasks, Ui ui) throws DukeException {
        tasks.add(task);
        ui.addTaskDialogue(task.toString(), tasks.size());
    } // End method.
}
