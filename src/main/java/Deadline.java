
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        SimpleDateFormat properFormat = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + properFormat.format(this.by)
                + ")";
    }
}
