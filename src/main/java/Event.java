import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDateTime dateTime;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            dateTime = LocalDateTime.parse(at, f);
        } catch (DateTimeParseException e) {
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
