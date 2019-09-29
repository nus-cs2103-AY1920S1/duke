package duke.task;

import java.util.Date;

/**
 * Represents an Event, a type of Task that has an expected date of occurrence.
 */
public class Event extends Task {
    private Date at;

    /**
     * Constructs a new Event, with the specified description and date of occurrence.
     * @param description Description of the Event.
     * @param at Expected date of occurrence of the Event.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
        this.type = TaskType.EVENT;
    }

    /**
     * Returns the String representation of an Event for display purposes.
     * Adds the date of occurrence of the Event to the String representation
     * provided by the Task class.
     * @return String representation of a Event for display purposes.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
