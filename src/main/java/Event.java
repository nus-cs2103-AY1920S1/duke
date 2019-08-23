import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date start;
    protected Date end;

    public Event(String description, Date start, Date end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        SimpleDateFormat startFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat endFormat = new SimpleDateFormat(" - HH:mm");
        return "[E]" + super.toString() + " (at: " + startFormat.format(start) + endFormat.format(end) + ")";
    }
}