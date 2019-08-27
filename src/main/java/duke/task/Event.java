package duke.task;

public class Event extends Task {
    private String period;

    public Event(String description, String period) {
        super(description);
        this.period = period;
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