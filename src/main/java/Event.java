import java.util.Date;

public class Event extends Task {
    protected String at;
    protected Date date;

    public Event(String description, String at, Date date) {
        super(description);
        this.at = at;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at +")";
    }
}
