package duke.task;

import java.util.Date;

/**
 * Task containing information about an event.
 */
public class Event extends Task {
    /**
     * Constructor for Event object.
     * 
     * @param description Description of event.
     * @param date Date of event.
     */
    public Event(String description, Date date) {
        super("E", description, date);
    }

    /**
     * Returns String containing information about the event.
     * 
     * @return String containing status, description and date of deadline.
     */
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.type,
                this.getStatusIcon(),
                this.description,
                this.date.toString());
    }
}
