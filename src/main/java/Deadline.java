import java.util.Date;

public class Deadline extends Task {
    protected String by;
    protected Date date;

    public Deadline(String description, String by, Date date) {
        super(description);
        this.by = by;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
