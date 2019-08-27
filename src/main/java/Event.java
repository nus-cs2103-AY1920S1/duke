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
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy hh:mm a");
        String date = formatter.format(at);
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
