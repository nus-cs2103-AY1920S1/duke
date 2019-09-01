import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline which a user is to meet.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Construct a task object that has a deadline to meet.
     * @param description Description of task
     * @param by Date and time to be due
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDate() {
        return this.by;
    }

    /**
     * Returns a date and time object of the specific date.
     *
     * @return Date and time Object.
     */
    public LocalDateTime getLocalDateTime() {
        String dateStr = this.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        return dateTime;
    }

    /**
     * Returns a formatted date and time for printing.
     *
     * @return Formatted date and time.
     */
    public String getDateTimePrint() {
        LocalDateTime dateTime = this.getLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTimePrint() + ")";
    }
}
