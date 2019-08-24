import java.util.Date;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private Date date;
    private LocalTime time;

    public Event(String description, Date date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return "[E]" + super.toString() + " (at: " + dateFormatter.format(date)  + " "
                + timeFormatter.format(time) + ")";
    }
}
