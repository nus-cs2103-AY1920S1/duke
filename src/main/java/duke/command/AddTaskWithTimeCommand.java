package duke.command;

import java.time.LocalDateTime;

/**
 * Command that adds a new task with a datetime field.
 */
public abstract class AddTaskWithTimeCommand extends AddTaskCommand {
    protected final LocalDateTime time;

    public AddTaskWithTimeCommand(final String description, final LocalDateTime time) {
        super(description);
        this.time = time;
    }
}
