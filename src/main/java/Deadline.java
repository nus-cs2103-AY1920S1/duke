import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline task given by the user.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Deadline(String s, String by) throws DukeException {
        super(s);
        try {
            this.by = LocalDateTime.parse(by, dateTimeFormatter);
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
        return String.format("[D][%s] %s (by: %s)", mark, taskDescription, by.format(dateTimeFormatter).toString());
    }
}
