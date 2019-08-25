package jermi.task;

/**
 * A representation of the event task.
 */
public class Event extends TaskWithDateTime {

    /**
     * Public constructor for class.
     * Creates an uncompleted event task.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     */
    public Event(String description, String dateTime) {
        this(description, dateTime, "0");
    }

    /**
     * Public constructor for class.
     * Creates a completed/uncompleted event task.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     * @param isDone "1" for completed event, else "0".
     */
    public Event(String description, String dateTime, String isDone) {
        super(description, dateTime, isDone);
    }

    /**
     * Returns task type code, "E".
     *
     * @return "E".
     */
    @Override
    String getTypeCode() {
        return "E";
    }

    /**
     * Returns a string representation of task.
     *
     * @return A string representation of task.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.getDateTime());
    }
}
