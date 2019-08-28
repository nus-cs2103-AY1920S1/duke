import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimeTask extends Task {
    private LocalDateTime time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    TimeTask(String description, String timeString) {
        super(description);
        time = LocalDateTime.parse(timeString, formatter);
    }

    String getSaveTimeString() {
        return formatter.format(time);
    }

    String getTimeString() {
        return DateTimeFormatter.ofPattern("yyyy MMMM dd HHmm").format(time);
    }
}
