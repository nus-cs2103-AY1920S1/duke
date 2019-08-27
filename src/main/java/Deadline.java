import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /** Deadline constructor.
     *
     * @param description is the description of the Task
     * @param date is the date of Task
     * @param time is the time of Task
     */
    public Deadline(String description, String date, String time) {
        super(description);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyy"));
        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toDataFormat() {
        return "D | " + super.toDataFormat() + " | " + date.format(DateTimeFormatter.ofPattern("d/MM/yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + ", " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
