package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for events.
 */
public class Event extends Task {
    /**
     * Variable for date and time.
     */
    private LocalDateTime dateTime;
    private DateTimeFormatter formatter;

    /**
     * Constructor for event.
     *
     * @param description description of event
     * @param date        date and time of event in String format
     */
    public Event(String description, String date) {
        super(description);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        dateTime = LocalDateTime.parse(date, formatter);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns String in text file format.
     *
     * @return a String to write on text file
     */
    public String toFile() {
        return "E|" + getStatusIcon() + "|" + description + "|" + dateTime.format(formatter);
    }

    /**
     * Returns String to output on terminal.
     *
     * @return a String to output on terminal
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + "(at: " + dateTime.format(formatter) + ")";
    }
}
