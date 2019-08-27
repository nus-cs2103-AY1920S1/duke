/**
 * Represents a task categorised as event. An Event object
 * includes a date to specify when the event will happen
 */
public class Event extends Task {
    protected DateTime at;

    public Event(String description, DateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
