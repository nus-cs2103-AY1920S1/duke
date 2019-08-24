import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        String isDone = this.isDone ? "1" : "0";
        return "D | " +  isDone + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + dateFormat.format(this.by) + ")";
    }
}
