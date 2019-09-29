/**
 * Represents a task categorised as event. An Event object
 * includes a date to specify when the event will happen
 */
public class Event extends Task {
    protected DateTime at;

    /**
     * Represents an event task.
     * @param description description of task
     * @param at date and time of task
     */
    public Event(String description, DateTime at) {
        super(description);
        assert at != null : "the dateTime of an event cannot be empty";
        this.at = at;
    }

    public void changeDateTime(DateTime by) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
