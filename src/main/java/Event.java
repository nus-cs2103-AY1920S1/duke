import java.util.Date;

/**
 * Represents an Event kind of task.
 * In addition to the information in the base Task object,
 * also contains a Date object representing when the event occurs.
 */
public class Event extends Task {
    protected Date at;

    /**
     * Constructor for Event. Assumes it is undone.
     *
     * @param description Description of the Event.
     * @param by Date and time when the Event occurs.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for Event. Allows user to indicate whether it is done.
     *
     * @param description Description of Event.
     * @param isDone Boolean representing whether the Event is done.
     * @param by Date and time when the Event occurs.
     */
    public Event(String description, boolean isDone, Date at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Represents the done status, description, and datetime of the task as a string,
     * with a flag indicating it is an Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")";
    }

    /**
     * Represents the Event as a string in a format suitable for data storage.
     *
     * @return Data storage-friendly string representation of the Event.
     */
    @Override
    public String toStorageString() {
        return "E`" + super.toStorageString() + '`' + at.toString();
    }
}
