import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event Class.
 *
 * Represents the event-type task.
 *
 * @author Marcus Ong
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected boolean isAllDay;

    public Event(String description, LocalDateTime startDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
        this.isAllDay = false;
    }

    public Event(String description, LocalDateTime startDateTime, boolean isAllDay) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = startDateTime;
        this.isAllDay = isAllDay;
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAllDay = false;
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime,
                 boolean isAllDay) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAllDay = isAllDay;
    }

    @Override
    public String toString() {
        if (isAllDay && startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return String.format("[E] %s (at: %s)", super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        } else if (isAllDay){
            return String.format("[E] %s (at: %s - %s)", super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        } else if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            return String.format("[E] %s (at: %s)", super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        } else {
            return String.format("[E] %s (at: %s - %s)", super.toString(),
                    startDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)),
                    endDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
    }
}