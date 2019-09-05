package duke.command;

import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

import static java.lang.System.lineSeparator;

/**
 * Represents a {@link Command} to delete a {@link Task}.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task with the specified ID" + System.lineSeparator()
            + "Usage: " + COMMAND_WORD + " <id>";
    private final int toDelete;

    /**
     * Creates a {@link Command} that deletes a {@link Task} when executed.
     *
     * @param id the id of the {@link Task} to be deleted.
     */
    public DeleteCommand(int id) {
        this.toDelete = id;
    }

    @Override
    public CommandResult execute() throws IOException {
        if (toDelete < 0 || toDelete >= tasks.size()) {
            return new IncorrectCommand(MESSAGE_USAGE).execute();
        }
        final Task task = tasks.delete(toDelete);
        storage.save(tasks.stringify());
        return new CommandResult("Noted. I've removed this task:"
                + lineSeparator() + Ui.INDENT + task
                + lineSeparator() + "Now you have " + tasks.size() + " tasks in the list."
        );
    }
}
