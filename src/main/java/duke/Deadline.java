package duke;

import java.time.LocalDateTime;

class Deadline extends Task {
    private LocalDateTime by;

    Deadline(final String description, final LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.dateTimeFormatter.format(this.by) + ")";
    }
}
