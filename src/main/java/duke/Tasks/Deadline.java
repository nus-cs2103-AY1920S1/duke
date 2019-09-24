package duke.tasks;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a Deadline Task which extends the Task Class
 * a Deadline Task object is represented by a String description and
 * a LocalDateTime which tells when the deadline task is due.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = LocalDateTime.parse(by, format1);
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(format1) + ")";
    }
}
