package duke;

import java.time.LocalDateTime;

class Event extends Task {
    private LocalDateTime at;

    Event(final String description, final LocalDateTime at, final boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    Event(final String description, final LocalDateTime at) {
        this(description, at, false);
    }

    String getAtString() {
        return Task.dateTimeFormatter.format(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAtString() + ")";
    }

    @Override
    String toStorageString() {
        String[] tokens = new String[]{
            "E",
            this.isDone() ? "1" : "0",
            this.getDescription(),
            this.getAtString()
        };
        return String.join(Todo.storageStringSeparator, tokens);
    }
}
