import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected DateFormat outDateFormat = new SimpleDateFormat( "dd/MM/yyyy");
    protected DateFormat outTimeFormat = new SimpleDateFormat("HH.mm aa");
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
}
