package duke;

import java.time.LocalDateTime;

class Event extends Task {
    private LocalDateTime at;

    Event(final String description, final LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Task.dateTimeFormatter.format(this.at) + ")";
    }
}
