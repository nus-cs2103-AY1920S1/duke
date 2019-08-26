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
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy, hmma");
        String date = formatter.format(by);
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}