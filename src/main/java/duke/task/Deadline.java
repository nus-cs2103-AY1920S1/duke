package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(final String description, final LocalDateTime by, final boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(final String description, final LocalDateTime by) {
        this(description, by, false);
    }

    private String getByString() {
        return Task.DATE_TIME_FORMATTER.format(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }

    @Override
    public String toStorageString() {
        String[] tokens = new String[]{
            "D",
            this.isDone() ? "1" : "0",
            this.getDescription(),
            this.getByString()
        };
        return String.join(Todo.STORAGE_STRING_SEPARATOR, tokens);
    }
}
