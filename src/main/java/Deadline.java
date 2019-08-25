import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected Date by;

    Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    private String stringDate() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + stringDate() + ")";
    }

    public String toFile() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + stringDate();
    }
}