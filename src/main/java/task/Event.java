package task;

/**
 * Handles Event type Task with specific time period.
 */
public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return description + " | " + time;
    }

    @Override
    public String toString() {
        return "[E]"  + super.toString() + " (at: " + time + ")";
    }
}
