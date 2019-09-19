import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    String getAt() {
        return dateFormat.format(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormat.format(this.at) + ")";
    }

}