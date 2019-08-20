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
        super(description, TaskType.EVENT);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone, TaskType.EVENT);
    }

    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getTag() + "]" + super.toString() + " (at: " + at + ")";
    }
}