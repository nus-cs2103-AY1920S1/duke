import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + dateFormat.format(this.at) + ")";
    }
}
