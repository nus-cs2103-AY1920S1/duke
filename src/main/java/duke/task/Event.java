package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(final String description, final LocalDateTime at, final boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Event(final String description, final LocalDateTime at) {
        this(description, at, false);
    }

    private String getAtString() {
        return Task.DATE_TIME_FORMATTER.format(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAtString() + ")";
    }

    @Override
    public String toStorageString() {
        String[] tokens = new String[]{
            "E",
            this.isDone() ? "1" : "0",
            this.getDescription(),
            this.getAtString()
        };
        return String.join(Todo.STORAGE_STRING_SEPARATOR, tokens);
    }
}
