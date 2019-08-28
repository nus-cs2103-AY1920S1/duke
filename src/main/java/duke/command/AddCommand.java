package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a {@link Command} to add a {@link Task}.
 */
public class AddCommand extends Command {

    private final Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public void execute() throws DukeException {
        tasks.add(toAdd);
        String data = toAdd.stringify();
        if (tasks.size() > 1) {
            data = System.lineSeparator() + data;
        }
        storage.append(data);
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + toAdd);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}
