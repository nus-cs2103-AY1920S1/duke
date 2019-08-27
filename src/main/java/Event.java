/*
 * Event.java
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a Event Task.
 *
 */

<<<<<<< HEAD
import java.io.Serializable;

public class Event extends Task implements Serializable {
    protected String at;
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime at;
    protected static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
>>>>>>> branch-Level-8

    public Event(String description) throws DukeException {
        super(description);
        String[] splitDescription = description.split(" /at ", 2);
        try {
            this.description = splitDescription[0];
            this.at = LocalDateTime.parse(splitDescription[1], dateFormatter);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a deadline using \"/at\".");
        }
        catch (DateTimeParseException e) {
            throw new DukeException("Please enter a date with the format dd/MM/yyyy HHmm.\n" + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(dateFormatter) + ")";
    }
}
