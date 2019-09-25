package duke.task;

import java.time.LocalDateTime;

/**
 * Task that starts at a certain datetime.
 */
public class Event extends Task {
    private LocalDateTime at;

    public Event(final String description, final LocalDateTime at, final boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Event(final String description, final LocalDateTime at) {
        this(description, at, false);
    }

    public String getAtString() {
        return DATE_TIME_FORMATTER.format(this.at);
    }

    @Override
    public String toString() {
        return this.getDescription() + " (at: " + this.getAtString() + ")";
    }
}
