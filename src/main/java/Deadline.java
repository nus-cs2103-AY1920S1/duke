import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Deadline Class.
 *
 * Represents the deadline-type task.
 *
 * @author Marcus Ong
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected boolean isAllDay;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.isAllDay = false;
    }

    public Deadline(String description, LocalDateTime by, boolean isAllDay) {
        super(description);
        this.by = by;
        this.isAllDay = isAllDay;
    }

    @Override
    public String toString() {
        if (isAllDay) {
            return String.format("[D] %s (by: %s)", super.toString(),
                    by.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else {
            return String.format("[D] %s (by: %s)", super.toString(),
                    by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}