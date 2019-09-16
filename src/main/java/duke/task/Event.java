package duke.task;

/**
 * Encapsulates an event task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event object with description, and date and time.
     *
     * @param description  Description of event.
     * @param at  Date and time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts the event task to String format to write to hard disk.
     *
     * @return Event task in String format.
     */
    @Override
    public String convertTaskToString() {
        return String.format("E | %s | %s | %s", getStatus(), description, at);
    }

    /**
     * Converts the event task to String format for output.
     *
     * @return Event task in String format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
