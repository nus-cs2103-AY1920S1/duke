import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline which a user is to meet.
 */
public class Deadline extends TaskWithDateTime {

    /**
     * Construct a task object that has a deadline to meet.
     * @param description Description of task
     * @param dateTime Date and time to be due
     */
    public Deadline(String description, String dateTime) {
        super(description, dateTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTimePrint() + ")";
    }
}
