import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected String by;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.symbol = "D";
    }

    public Date getDate() throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy hhmm");
        Date date = format.parse(by);
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}