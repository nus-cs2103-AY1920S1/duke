import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Deadline extends Task {
    
    protected Date by;
    
    /**
     * Deadline class as part of Task.
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date1 = date.parse(by);
        this.by = date1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
