import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline task given by the user.
 */
public class Deadline extends TimedTask {

    public Deadline(String s, String by) throws DukeException {
        super(s, by);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (isDone ? "Complete" : "Incomplete"),
                taskDescription, super.timestamp.format(super.dateTimeFormatter));
    }
}
