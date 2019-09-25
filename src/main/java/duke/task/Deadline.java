package duke.task;

import java.time.LocalDateTime;

/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(final String description, final LocalDateTime by, final boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(final String description, final LocalDateTime by) {
        this(description, by, false);
    }

    public String getByString() {
        return DATE_TIME_FORMATTER.format(this.by);
    }

    @Override
    public String toString() {
        return this.getDescription() + " (by: " + getByString() + ")";
    }
}
