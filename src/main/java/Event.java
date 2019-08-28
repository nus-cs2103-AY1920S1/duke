import java.text.ParseException;

public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public String repr() throws ParseException {
        return "[E][" + getStatusIcon() + "] " + super.formatDescription();
    }
}
