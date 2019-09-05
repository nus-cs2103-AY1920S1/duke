package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import static java.lang.System.lineSeparator;

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
    public CommandResult execute() throws DukeException {
        try {
            Task task = tasks.get(toMarkAsDone);
            task.markAsDone();
            storage.save(tasks.stringify());
            return new CommandResult("Nice! I've marked this task as done:"
                    + lineSeparator() + ui.INDENT + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Marking task with ID " + toMarkAsDone + " failed: Invalid ID");
        }
    }
}
