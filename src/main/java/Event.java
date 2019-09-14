import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the event task given by the user.
 */
public class Event extends TimedTask {

    public Event(String s, String at) throws DukeException {
        super(s, at);
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", (isDone ? "Complete" : "Incomplete"),
                taskDescription, super.timestamp.format(super.dateTimeFormatter));
    }
}
