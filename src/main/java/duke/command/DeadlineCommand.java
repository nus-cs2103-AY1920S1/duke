package duke.command;

import duke.task.Deadline;

import java.util.Date;

/**
 * Represents a {@link Command} to add a {@link Deadline}.
 */
public class DeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a task with a deadline" + System.lineSeparator()
            + "Usage: " + COMMAND_WORD + " <description> /by <dueDate>";

    public DeadlineCommand(String description, Date dueDate) {
        super(new Deadline(description, dueDate));
    }
}
