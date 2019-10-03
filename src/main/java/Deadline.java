import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date by;

    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    String getBy() {
        return dateFormat.format(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormat.format(this.by) + ")";
    }
}