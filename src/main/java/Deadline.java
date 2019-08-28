import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected String by;
    protected Date date;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
    }

    public Deadline(String description, boolean isDone, String by) throws ParseException {
        super(description, isDone);
        this.by = by;
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy, hmma");
        String formattedDate = formatter.format(this.date);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String print() {
        if (this.isDone) {
            return "D @ 1 @ " + this.description + " @ " + this.by;
        } else {
            return "D @ 0 @ " + this.description + " @ " + this.by;
        }
    }
}