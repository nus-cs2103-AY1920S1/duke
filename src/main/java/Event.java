import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime at, to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String content, int status, LocalDateTime at, LocalDateTime to) {
        super(content, status);
        this.at = at;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + " to " + to.format(formatter) + ")";
    }

    public String toFile() {
        return "E," + super.toFile() + "," + at.format(formatter) + "," + to.format(formatter);
    }
}