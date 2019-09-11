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
     * Constructor for deadline.
     *
     * @param description description of deadline
     * @param date        date and time of deadline in String format
     */
    public Deadline(String description, String date) {
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
