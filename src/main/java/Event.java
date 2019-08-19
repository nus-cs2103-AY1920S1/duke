/**
 * Event Class.
 *
 * Represents the event-type task.
 *
 * @author Marcus Ong
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, "E");
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone, "E");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}