import java.util.Date;

/** A type of task called Event. */
public class Event extends Task {
    protected Date at;

    public Event(String description, String at) {
        super(description);
        this.at = Task.parseDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Task.stringifyDate(at) + ")";
    }
}
