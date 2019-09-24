import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is a subclass of Task class.
 * A deadline needs the LocalDateTime to specify when is the deadline due.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getDeadlineTime() {
        return this.by;
    }

    public String getTaskBy() {
        return this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(myFormatter) + ")";
    }
}