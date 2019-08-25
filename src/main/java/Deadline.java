import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected DateFormat outDateFormat = new SimpleDateFormat( "dd/MM/yyyy");
    protected DateFormat outTimeFormat = new SimpleDateFormat("H.mm aa");
    protected SimpleDateFormat fileDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected SimpleDateFormat fileTimeFormat = new SimpleDateFormat("HHmm");
    protected Date date, time;

    public Deadline(String description, Date date, Date time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outDateFormat.format(date) + " " + outTimeFormat.format(time) + ")";
    }

    @Override
    public String toWriteIntoFile() {
        return "D" + super.toWriteIntoFile() + " | " + fileDateFormat.format(date) + " " + fileTimeFormat.format(time);
    }
}
