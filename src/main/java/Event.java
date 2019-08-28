import java.util.Objects;

public class Event extends Task{
    protected String at;
    protected Date atDate;
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, Date atDate) {
        super(description);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        String str = Objects.isNull(atDate) ? at : atDate.toString();
        return "[E]["+ this.getStatusIcon() +"] " + super.toString() + " (at: " + str + ")";
    }

    @Override
    public String toSave() {
        String done = isDone ? "1 | " : "0 | ";
        return "T | " + done + description + " | " + at;
    }
}
