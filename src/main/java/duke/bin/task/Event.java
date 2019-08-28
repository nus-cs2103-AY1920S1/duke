package duke.bin.task;

public class Event extends Task {
    protected String description;

    public Event(String task, String description) {
        super(task);
        this.description = description;
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + description + ")";
    }
}
