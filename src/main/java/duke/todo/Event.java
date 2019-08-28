package duke.todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    /**
     * Constructs an Event based on the description and the date.
     *
     * @param description Detail of the event.
     * @param date Date of the event.
     */
    public Event(String description, String date) {
        super(description);

        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.date = LocalDateTime.parse(date, formatter);
    }

    /**
     * Returns a formatted string of this event.
     *
     * @return Formatted string of this event.
     */
    @Override
    public String getFormattedTask() {
        return "E | " + super.getDescription() +
                " /at " + date;
    }

    /**
     * Returns a string of this event for display usage.
     *
     * @return Formatted string of this event for display.
     */
    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + this.getDescription() +
                " (on: " + date.format(formatter) + ")";
    }
}
