import java.text.ParseException;

public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    public String repr() throws ParseException {
        return "[E][" + getStatusIcon() + "] " + super.formatDescription();
    }

}
