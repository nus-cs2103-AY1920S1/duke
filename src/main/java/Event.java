import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, Date at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.toString() + ")";
    }

    @Override
    public String toStorageString() {
        return "E`" + super.toStorageString() + '`' + at.toString();
    }
}
