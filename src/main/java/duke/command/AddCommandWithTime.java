package duke.command;

import java.time.LocalDateTime;

public abstract class AddCommandWithTime extends AddCommand {
    protected final LocalDateTime time;

    public AddCommandWithTime(final String description, final LocalDateTime time) {
        super(description);
        this.time = time;
    }
}
