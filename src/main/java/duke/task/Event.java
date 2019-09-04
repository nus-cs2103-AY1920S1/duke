package duke.task;

public class Event extends Task {
    public Event(String description, String extraInfo) {
        super(description);
        this.extraInfo = extraInfo;
        this.type = "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.extraInfo + ")";
    }
}
