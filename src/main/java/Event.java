import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected Date atTime;

    public Event(String description, Date atTime) {
        super(description);
        this.atTime = atTime;
    }

    public Date getTime() {
        return this.atTime;
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its time period to the base toString() representation
        return String.format("[E]%s (at: %s)", super.toString(), DATE_FORMATTER.format(this.atTime));
    }
}
