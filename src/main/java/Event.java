import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate start_date;
    private LocalTime start_time;
    private LocalDate end_date;
    private LocalTime end_time;

    public Event(String description, String at) {
        super(description);
        String[] dateTimeArr = at.split(" ");
        this.start_date = parseDate(dateTimeArr[0]);
        this.start_time = parseTime(dateTimeArr[1]);
        if (dateTimeArr.length == 3) {
            this.end_date = parseDate(dateTimeArr[0]);
            this.end_time = parseTime(dateTimeArr[2]);
        } else {
            this.end_date = parseDate(dateTimeArr[2]);
            this.end_time = parseTime(dateTimeArr[3]);
        }
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String formatAt() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");

        if (start_date.equals(end_date)) {
            return String.format("%s, %s - %s", dateFormatter.format(start_date),
                    timeFormatter.format(start_time), timeFormatter.format(end_time));
        } else {
            return String.format("%s, %s - %s, %s", dateFormatter.format(start_date),
                    timeFormatter.format(start_time), dateFormatter.format(end_date),
                    timeFormatter.format(end_time));
        }
    }

    @Override
    public String serialize() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return String.format("E | %d | %s | %s %s %s %s", getStatusCode(), description ,
                dateFormatter.format(start_date), timeFormatter.format(start_time),
                dateFormatter.format(end_date), timeFormatter.format(end_time));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatAt() + ")";
    }
}