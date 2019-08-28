// Adapted from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html 

import java.util.Date;

/**
 * Class representation of events in the list.
 */
public class Event extends Task {
    protected Date at;

    /**
     * Creates a new Event.
     * 
     * @param description Description of the Event
     * @param at Date at which the Event occurs
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a String representation of this Event.
     * 
     * @return String representation of this Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Gets the Date at which this Event occurs.
     * 
     * @return Date at which the Event occurs
     */
    public Date getAt() {
        return this.at;
    }
}