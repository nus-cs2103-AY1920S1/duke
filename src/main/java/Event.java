import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String at;
    protected Date date;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        this.date = formatter.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
