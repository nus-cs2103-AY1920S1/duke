import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String at;
    protected Date date;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(at);
    }

    public String format() {
        return "E" + super.format() + "|" + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
