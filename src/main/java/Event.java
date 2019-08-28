import java.time.LocalDateTime;

/**
 * Encapsulates an event.
 * Each event has a time when the event takes place,
 * stored in both String and LocalDateTime formats.
 * A subclass of Task.
 */
public class Event extends Task {

    protected String at;
    protected LocalDateTime dateTimeAt = null;

    /**
     * Constructor.
     * @param description description of event
     * @param at date and time when event takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setDateTimeAt(LocalDateTime dateTimeAt) {
        this.dateTimeAt = dateTimeAt;
    }

    /**
     * Overridden toString method. Converts a event object into string form to be used
     * in to-do list display.
     * @return string representation of a task on the to-do list
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Overridden toTextFileString method. Converts a task object into string form to be used
     * in storage text file.
     * @return string representation of a task on the storage text file
     */
    @Override
    public String toTextFileString() {
        return "E|" + super.toTextFileString() + "|" + at;
    }
}