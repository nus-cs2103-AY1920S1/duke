import java.util.Date;

public class Event extends Task {

    protected String at;

    protected Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;

        this.date = DateClass.stringToDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
