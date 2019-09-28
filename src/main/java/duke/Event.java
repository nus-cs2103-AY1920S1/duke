package duke;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents an Event task.
 */
public class Event extends Task implements Serializable {
    private LocalDateTime at;
    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates an event task.
     * @param description indicates what the task is about and when the event is
     * @throws DukeException when input does not meet required format
     *     or '/at' is not specified.
     */
    public Event(String description) throws DukeException {
        super(description);
        String[] splitDescription = description.split(" /at ", 2);
        try {
            this.description = splitDescription[0];
            this.at = LocalDateTime.parse(splitDescription[1], dateFormatter);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter the event date using \"/at\","
                    + " followed by the deadline in \"dd/MM/yyyy HHmm\".");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a date with the format "
                    + "dd/MM/yyyy HHmm.\n" + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the Event Task.
     * @return a string representation of the Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(dateFormatter) + ")";
    }
}
