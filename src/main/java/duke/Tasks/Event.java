package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task which extends the Task Class
 * a Event Task object is represented by a String description and
 * a LocalDateTime which tells when the Event task is at.
 */

public class Event extends Task {
    protected LocalDateTime at;
    protected DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, format1);
    }

    public LocalDateTime getDateTime() {
        return at;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(format1) + ")";
    }
}
