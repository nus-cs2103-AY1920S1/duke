package duke.todo;

import duke.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter;

    /**
     * Constructs an Event based on the description and the eventDate.
     *
     * @param description Detail of the event.
     * @param eventDate Date of the event.
     */
    public Event(String description, LocalDateTime eventDate) {
        super(description);

        this.formatter = DateTimeFormatter.ofPattern(Parser.DATE_FORMAT);
        this.date = eventDate;
    }

    /**
     * Returns a formatted string of this event.
     *
     * @return Formatted string of this event.
     */
    @Override
    public String getFormattedTask() {
        return "E | " + super.getDescription()
                + " /at " + date;
    }

    /**
     * Returns a string of this event for display usage.
     *
     * @return Formatted string of this event for display.
     */
    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + this.getDescription()
                + " (on: " + date.format(formatter) + ")";
    }
}
