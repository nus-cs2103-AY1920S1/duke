import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimeTask extends Task {
    private LocalDateTime time;

    TimeTask(String description, String timeString) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        time = LocalDateTime.parse(timeString, formatter);
    }

    String getTimeString() {
        return DateTimeFormatter.ofPattern("yyyy MMMM dd HHmm").format(time);
    }
}
