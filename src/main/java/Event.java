import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected DateFormat outDateFormat = new SimpleDateFormat( "dd/MM/yyyy");
    protected DateFormat outTimeFormat = new SimpleDateFormat("HH.mm aa");
    protected Date date, time;
    public Event(String description, Date date, Date time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + outDateFormat.format(date) + " " + outTimeFormat.format(time) + ")";
    }

    @Override
    public String toWriteIntoFile() {
        return "E" + super.toWriteIntoFile() + " | " + at;
    }
}
