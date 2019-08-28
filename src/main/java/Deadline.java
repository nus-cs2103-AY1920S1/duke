import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task that categorized itself as an important thing to-do
 * before a particular time.
 *
 * @param description description of the Deadline (Task).
 * @param by the date of the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    // to manage incoming deadline list at specific time
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    // get the date and time with specific format
    public String getBy() {
        return by.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(dateTimeFormatter) + ")";
    }
}