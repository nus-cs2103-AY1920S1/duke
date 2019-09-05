package duke.command;

import duke.exception.DukeException;
import duke.task.Task;

import static java.lang.System.lineSeparator;

/**
 * Represents a {@link Command} to delete a {@link Task}.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int toDelete;

    public DeleteCommand(int id) {
        this.toDelete = id;
    }

    @Override
    public CommandResult execute() throws DukeException {
        final Task task = tasks.delete(toDelete);
        storage.save(tasks.stringify());
        return new CommandResult("Noted. I've removed this task:"
                + lineSeparator() + ui.INDENT + task
                + lineSeparator() + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
