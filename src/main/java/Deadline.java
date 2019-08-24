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

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(date)  + " "
                + timeFormatter.format(time) + ")";
    }
}
