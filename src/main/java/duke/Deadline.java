package duke;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Deadline task.
 */
public class Deadline extends Task implements Serializable {
    private LocalDateTime by;
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a deadline task.
     * @param description indicates what the task is about
     *     and when the deadline is
     * @throws DukeException if input does not meet require
     *     format or '/by' is not specified.
     */
    public Deadline(String description) throws DukeException {
        super(description);
        String[] splitDescription = description.split(" /by ", 2);

        try {
            this.description = splitDescription[0];
            this.by = LocalDateTime.parse(splitDescription[1], dateFormatter);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline using \"/by\","
                    + " followed by the deadline in \"dd/MM/yyyy HHmm\".");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a date with the format "
                    + "\"dd/MM/yyyy HHmm\".\n" + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the Deadline Task.
     * @return a string representation of the deadline Task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(dateFormatter) + ")";
    }
}
