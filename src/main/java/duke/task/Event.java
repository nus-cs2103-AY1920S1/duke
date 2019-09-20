package duke.task;

import duke.Duke;

import java.util.Date;

public class Event extends Task {
    private Date at;

    /**
     * Constructs a new event.
     *
     * @param description Description of the event.
     * @param at          Date of the event.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Generates the event's string representation.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Duke.DATE_FORMATTER.format(this.at));
    }
}
