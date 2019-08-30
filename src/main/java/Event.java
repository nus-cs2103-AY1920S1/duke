import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the event task given by the user.
 */
public class Event extends Task {
    private LocalDateTime at;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Event(String s, String at) throws DukeException {
        super(s);
        try {
            this.at = LocalDateTime.parse(at, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException("You formatted your time incorrectly! " +
                    "Please format it this way: dd/mm/yyyy hh:mm");
        }
    }

    /**
     * Formats the task such that it can be outputted in a readable form for the user.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[E][%s] %s (at: %s)", mark, taskDescription, at.format(dateTimeFormatter).toString());
    }
}
