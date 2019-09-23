package seedu.duke.tasks;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private static final long serialVersionUID = -25289058397902347L;

    private final LocalDateTime time;

    /**
     * Create an event task, with a specified time.
     *
     * @param description the event description
     * @param time the time of the event
     */
    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    /**
     * Get the time of the event.
     *
     * @return the event time
     */
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s%s", super.toString(),
                time != null
                        ? String.format(" (at: %s)", time.format(DATE_TIME_FORMATTER))
                        : ""
        );
    }
}
