import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected Date by;
    protected SimpleDateFormat format = new SimpleDateFormat();
    
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }
    
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        format = new SimpleDateFormat("EEEE, MMM d, HH:mm");
        return "[D]" + super.toString() + " (by: " + format.format(by) + ")";
    }
}