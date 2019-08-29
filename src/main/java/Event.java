import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String when;
    protected boolean isValid;

    public Event(String description, String when) {
        super(description);
        this.when = when;
    }


    @Override
    public String toString() {
        String date = formatDate(when);
        if (getIsCorrectFormat()) {
            return "[E]" + super.toString() + description + " (at: " + date + ")";
        } else {
            return "Invalid date format!";
        }
    }
}