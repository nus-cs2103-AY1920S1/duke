import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy, hmma");
        String date = formatter.format(at);
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
