package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private String at;
    private LocalDateTime time;

    /**
     * Constructs an event Task.
     *
     * @param description description of the event
     * @param at time and date of the event
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        String[] dateFormats = {"dd/MM/yyyy HHmm", "ddMMyy HHmm", "dd/MM/yy HHmm"};
        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                time = LocalDateTime.parse(at, formatter);
                return;
            } catch (Exception err) {
                // try next format
            }
        }
        throw new DukeException("Please enter your date/time format in \"dd/MM/yy HHmm\"");
    }

    /**
     * Converts the task into a StringBuilder object to write into storage.
     *
     * @return a StringBuilder object for file writing
     */
    @Override
    public StringBuilder saveData() {
        return new StringBuilder("E|").append(super.saveData()).append("|").append(at);
    }

    /**
     * Returns a string representation of this task.
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}