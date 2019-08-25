import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Date getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}