import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy Hmm"));
    }

    @Override
    public String printSave() {
        return "E | " + ((isDone) ? "1" : "0") + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}