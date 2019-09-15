package duke.task;

/**
 * Represents an event that occurs at a specific time. The <code>Evente</code> 
 * class inherits from the <code>Task</code> class.
 */
public class Event extends Task {

    /** A string that represents the time of the event, as inputted by user. */
    private String occurTime;

    /**
     * Constructs a <code>Event</code> object. Date and time are parsed and 
     * stored in <code>dateTime</code> field if input is of "dd/MM/yyyy HHmm"
     * format.
     *
     * @param description A string that describes the specific
     *          content of the task.
     * @param occurTime A string that specifies the time of the
     *          event.
     */
    public Event(String description, String occurTime) {
        super(description);
        this.occurTime = occurTime;
    }

    /**
     * Updates the time of occurrence of the event.
     *
     * @param newOccurTime The new time of occurrence to replace the existing one.
     */
    public void updateOccurTime(String newOccurTime) {
        this.occurTime = newOccurTime;
    }

    /**
     * Returns a string representation of the task to be stored in a local file.
     *
     * @return A string in a specific format for clear display in a local file.
     */
    @Override
    public String format() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.occurTime;
    }

    /**
     * Returns a string representation of the task to be printed upon user 
     * inquiry.
     *
     * @return A string representation of the task that displays the type,
     *          description and time of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + occurTime + ")";
    }
}
