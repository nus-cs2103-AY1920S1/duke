import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the task given by the user.
 */
public abstract class TimedTask extends Task {
    protected LocalDateTime timestamp;
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TimedTask(String s, String rawTimestamp) throws DukeException {
        super(s);
        try {
            this.timestamp = LocalDateTime.parse(rawTimestamp, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeException("You formatted your time incorrectly! " +
                "Please format it this way: dd/mm/yyyy hh:mm");
        }
    }

    @Override
    public boolean isTimed() {
        return true;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public abstract String toString();
}
