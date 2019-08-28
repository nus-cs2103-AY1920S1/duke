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
        SimpleDateFormat properFormat = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + properFormat.format(this.at)
                + ")";
    }
}
