package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline tasks.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;
    private DateTimeFormatter formatter;

    /**
     * Constructor for event.
     *
     * @param description description of event
     * @param date        date and time of event
     */
    public Deadline(String description, String date) {
        super(description);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        dateTime = LocalDateTime.parse(date, formatter);
    }

    /**
     * Returns String in text file format.
     *
     * @return a String to write on text file
     */
    public String toFile() {
        return "D|" + getStatusIcon() + "|" + description + "|" + dateTime.format(formatter);
    }

    /**
     * Returns String to output on terminal.
     *
     * @return a String to output on terminal
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + "(by: " + dateTime.format(formatter) + ")";
    }
}
