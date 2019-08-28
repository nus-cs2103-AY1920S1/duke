package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a {@link Command} to mark a {@link Task} as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private int toMarkAsDone;

    public DoneCommand(int id) {
        this.toMarkAsDone = id;
    }

    @Override
    public void execute() throws DukeException {
        try {
            Task task = tasks.get(toMarkAsDone);
            task.markAsDone();
            storage.save(tasks.stringify());
            ui.showLine();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage("  " + task);
            ui.showLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Marking task with ID " + toMarkAsDone + " failed: Invalid ID");
        }
    }
}
