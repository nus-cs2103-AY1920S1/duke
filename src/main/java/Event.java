import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    Date by;

    public Event(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
    }
}
