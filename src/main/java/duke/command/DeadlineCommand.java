package duke.command;

import duke.task.Deadline;

import java.util.Date;

/**
 * Represents a {@link Command} to add a {@link Deadline}.
 */
public class DeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String description, Date by) {
        super(new Deadline(description, by));
    }
}
