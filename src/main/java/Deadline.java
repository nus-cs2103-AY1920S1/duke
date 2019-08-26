import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * a deadline is a type of class with a by datetime
 */
public class Deadline extends Task {
    LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String content, int status, LocalDateTime by, int order) {
        super(content, status, order);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public String toFile() {
        return "D," + super.toFile() + "," + by.format(formatter);
    }
}