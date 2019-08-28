package duke;

import java.time.LocalDateTime;

class Deadline extends Task {
    private LocalDateTime by;

    Deadline(final String description, final LocalDateTime by, final boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    Deadline(final String description, final LocalDateTime by) {
        this(description, by, false);
    }

    String getByString() {
        return Task.dateTimeFormatter.format(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }

    @Override
    String toStorageString() {
        String[] tokens = new String[]{
            "D",
            this.isDone() ? "1" : "0",
            this.getDescription(),
            this.getByString()
        };
        return String.join(Todo.storageStringSeparator, tokens);
    }
}
