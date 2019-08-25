import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDate() {
        return this.by;
    }

    public LocalDateTime getLocalDateTime() {
        String dateStr = this.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

        return dateTime;
    }

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
