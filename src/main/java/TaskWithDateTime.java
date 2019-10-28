import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a date and time requirement.
 */

public class TaskWithDateTime extends Task {

    public static String DATE_FORMAT_FOR_INPUT = "d/M/yyyy HHmm";
    public static String DATE_FORMAT_FOR_PRINT = "d MMMM yyyy, h:mma";

    protected String dateTime;

    /**
     * Construct a task object that has date and time requirement.
     * @param description Description of task
     * @param dateTime Date and time to be due
     */
    public TaskWithDateTime(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDate() {
        return this.dateTime;
    }

    /**
     * Returns a date and time object of the specific date.
     *
     * @return Date and time Object.
     */
    public LocalDateTime getLocalDateTime() {
        String dateStr = this.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_INPUT);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_PRINT);
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }
}
