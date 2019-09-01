import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimeTask extends Task {
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    TimeTask(String description, String timeString) {
        super(description);
        time = LocalDateTime.parse(timeString, formatter);
    }

    /**
     * Returns the time attached to this task as a string for saving.
     *
     * @return Time as a string.
     */
    String getSaveTimeString() {
        return formatter.format(time);
    }

    /**
     * Returns the time attached to this task as a string to display to the user.
     *
     * @return Time as a string.
     */
    String getTimeString() {
        return DateTimeFormatter.ofPattern("yyyy MMMM dd HHmm").format(time);
    }
}
