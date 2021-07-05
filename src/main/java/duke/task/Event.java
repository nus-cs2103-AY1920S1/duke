package duke.task;

/**
 * Represents a task to be done at some specified time.
 */
public class Event extends Task {
    private String period;

    public Event(String description, String period) {
        this(description, period, false);
    }

    public Event(String description, String period, boolean isDone) {
        super(description, isDone);
        this.period = period;
    }

    public String getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ')';
    }
}