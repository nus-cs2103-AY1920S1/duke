import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    protected Date date;
    protected String at;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
    }

    public Event(String description, boolean isDone, String at) throws ParseException {
        super(description, isDone);
        this.at = at;
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy, hmma");
        String formattedDate = formatter.format(this.date);
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    @Override
    public String print() {
        if (this.isDone) {
            return "E @ 1 @ " + this.description + " @ " + this.at;
        } else {
            return "E @ 0 @ " + this.description + " @ " + this.at;
        }
    }
}
