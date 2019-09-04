package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

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
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            super.commandOutput = ui.addTaskDialogue(task.toString(), tasks.size() + 1);
            // Size + 1 since the addTask dialogue is being generated before it is added.
            tasks.add(task); // Add after task.toString() to see if there is formatting error.
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time should be dd/MM/yyyy HHmm!");
        }
    }
}
