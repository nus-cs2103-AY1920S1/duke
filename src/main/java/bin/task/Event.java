package bin.task;

public class Event extends Task {
    protected String description;

    public Event(String task, String description) {
        super(task);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + description + ")";
    }
}
