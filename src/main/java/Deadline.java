/*
 * Deadline.java
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a Deadline Task.
 *
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String description) throws DukeException {
        super(description);
        String[] splitDescription = description.split(" /by ", 2);

        try {
            this.description = splitDescription[0];
            this.by = LocalDateTime.parse(splitDescription[1], dateFormatter);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline using \"/by\".");
        }
        catch (DateTimeParseException e) {
            throw new DukeException("Please enter a date with the format dd/MM/yyyy HHmm.\n" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]"+ super.toString() + " (by: " + by.format(dateFormatter) + ")";
    }
}
