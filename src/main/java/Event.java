import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = parseDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
