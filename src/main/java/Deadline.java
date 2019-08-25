import java.util.Date;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private Date date;
    private LocalTime time;

    public Deadline(String description, Date date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getDateString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormatter.format(date);
    }

    public String getTimeString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return timeFormatter.format(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString()  + " "
                + getTimeString() + ")";
    }
}
