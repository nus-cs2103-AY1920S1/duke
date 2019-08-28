import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by_date;
    private LocalTime by_time;

    public Deadline(String description, String by) {
        super(description);
        String[] dateTimeArr = by.split(" ");
        this.by_date = parseDate(dateTimeArr[0]);
        this.by_time = parseTime(dateTimeArr[1]);
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String formatBy() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return String.format("%s, %s", dateFormatter.format(by_date), timeFormatter.format(by_time));
    }

    @Override
    public String serialize() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return String.format("D | %d | %s | %s %s", getStatusCode(), description ,
                dateFormatter.format(by_date), timeFormatter.format(by_time));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy() + ")";
    }
}