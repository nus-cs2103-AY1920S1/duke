import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected Date date;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = by;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        this.date = formatter.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hhmm");
        return formatter.parse(this.by);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
