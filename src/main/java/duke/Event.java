package duke;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Event class creates a Event task
 * that extends from the Task class.
 */
public class Event extends Task {

    private Date at;

    /**
     * Constructor for class Event.
     *
     * @param description The Event task.
     * @param at The date/time of event.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the formatted date and time of the event.
     *
     * @return at Date and time of event.
     */
    public String getAt() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        return dateTimeFormat.format(at);
    }

    /**
     * Overrides the toString method to print the event task.
     *
     * @return String Event task formatted to string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAt() + ")";
    }
}