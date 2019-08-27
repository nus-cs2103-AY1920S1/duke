package task;

import java.util.Date;

/**
 * Represents an event
 */
public class Event extends Task {

    protected Date at;

    /**
     * @param description of the event
     * @param at represents the date of the event
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * @param description of the event
     * @param at represents the date of the event
     * @param isDone true if event is completed. Else false
     */
    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Date getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}