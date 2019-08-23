import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}