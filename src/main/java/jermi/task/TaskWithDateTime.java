package jermi.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A representation of task with date and time.
 */
abstract class TaskWithDateTime extends Task {
    /** Date and time */
    private String dateTime;

    /**
     * Constructor for class.
     *
     * @param description Description of task.
     * @param dateTime Date and time of task.
     * @param isDone "1" for completed task, else "0".
     */
    TaskWithDateTime(String description, String dateTime, String isDone) {
        super(description, isDone);
        try {
            this.dateTime = this.parseStringFormatDateTime(dateTime);
        } catch (DateTimeParseException e) {
            this.dateTime = dateTime;
        }
    }

    /**
     * Converts date and time in string format to LocalDateTime format.
     *
     * @param string Date and time in string format.
     * @return Date and time in LocalDateTime format.
     */
    private LocalDateTime stringToDateTime(String string) {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Converts date and time in LocalDateTime format to string format.
     *
     * @param dateTime Date and time in LocalDateTime format.
     * @return Date and time in string format.
     */
    private String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h.mma"));
    }

    /**
     * Parses input user's data and time format.
     *
     * @param stringFormatDateTime Input user's date and time format.
     * @return Parsed date and time format.
     */
    private String parseStringFormatDateTime(String stringFormatDateTime) {
        return this.dateTimeToString(this.stringToDateTime(stringFormatDateTime));
    }

    /**
     * Returns the date and time of the task.
     *
     * @return Date and time.
     */
    String getDateTime() {
        return this.dateTime;
    }

    /**
     * Converts the task into a string in save format.
     *
     * @return A string in save format.
     */
    @Override
    public String toSaveFormat() {
        return String.format("%s|%s", super.toSaveFormat(), this.dateTime);
    }
}
