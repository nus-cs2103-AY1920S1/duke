import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date at;

    Event(String description, boolean isDone, Date at) {
        super(description, isDone);
        this.at = at;
    }

    Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    private String stringDate() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + stringDate() + ")";
    }

    public String toFile() {
        return "E | " + super.getStatusIcon() + " | " + description + " | " + stringDate();
    }
}