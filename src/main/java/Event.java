import java.util.Date;

public class Event extends Task {

    protected String at;
    protected Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = DateParser.dateParser(at);
        if (date != null) { this.at = date.toString(); }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getDetails() {return "E @ " + super.getDetails() + " @ " + at; }
}