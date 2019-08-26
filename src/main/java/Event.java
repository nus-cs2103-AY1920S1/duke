import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * event is a type of task with from and to datetimes
 */
public class Event extends Task {
    LocalDateTime at, to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String content, int status, LocalDateTime at, LocalDateTime to, int order) {
        super(content, status, order);
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