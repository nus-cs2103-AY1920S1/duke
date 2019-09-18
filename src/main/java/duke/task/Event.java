package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Creates an Event Task. Description and timing required.
 */
public class Event extends Task {
    private String at;
    private Date date;

    /**
     * Creates an Event Task. Description and timing required.
     *
     * @param description Description of the task, in String.
     * @param at Timing of the task, in String.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = new Date(at);
    }

    private LocalDateTime formatDate(String at) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(at, formatter);
    }

    @Override
    public String toWriteFile() {
        return "E | " + getDoneStatus() + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toString() + ")";
    }
}
