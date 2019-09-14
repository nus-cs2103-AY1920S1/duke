import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the task given by the user.
 */
abstract class TimedTask extends Task {
    LocalDateTime timestamp;
    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    TimedTask(String taskDescription, String rawTimestamp) throws DukeException {
        super(taskDescription);
        try {
            timestamp = LocalDateTime.parse(rawTimestamp, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException("You formatted your time incorrectly! " +
                "Please format it this way: dd/mm/yyyy hh:mm");
        }
    }

    @Override
    boolean isTimed() {
        return true;
    }

    LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public abstract String toString();

}
