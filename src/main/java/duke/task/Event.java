package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        this(description, at, false);
    }

    /**
     * Constructs an Event task object.
     * @param description Description of event.
     * @param at DateTime of event.
     * @param isDone Whether an event is done.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns time of Event.
     * @return Time of Event.
     */
    public String getTime() {
        return DATE_TIME_FORMATTER.format(this.at);
    }

    /**
     * Returns a String representing the task, of format "[E][✘] task (at: date time)".
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DATE_TIME_FORMATTER.format(this.at));
    }
}
