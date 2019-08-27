package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        this(description, at, false);
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns time of duke.task.Event.
     * @return Time of duke.task.Event.
     */
    public String getTime() {
        return DATE_TIME_FORMATTER.format(this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DATE_TIME_FORMATTER.format(this.at));
    }
}
