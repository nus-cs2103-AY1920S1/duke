package duke.command;

import duke.task.Task;
import duke.ui.Ui;

import java.io.IOException;

import static java.lang.System.lineSeparator;

/**
 * Represents a {@link Command} to mark a {@link Task} as done.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task with the specified ID as complete"
            + lineSeparator() + "Usage: " + COMMAND_WORD + " <id>";

    private int toMarkAsDone;

    /**
     * Creates a {@link Command} that marks a {@link Task} as complete when executed.
     *
     * @param id the id of the task to be marked as complete
     */
    public DoneCommand(int id) {
        this.toMarkAsDone = id;
    }

    @Override
    public CommandResult execute() throws IOException {
        if (toMarkAsDone < 0 || toMarkAsDone >= tasks.size()) {
            return new IncorrectCommand(MESSAGE_USAGE).execute();
        }
        Task task = tasks.get(toMarkAsDone);
        task.markAsDone();
        storage.save(tasks.stringify());
        return new CommandResult("Nice! I've marked this task as done:" + lineSeparator() + Ui.INDENT + task);
    }
}
