import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTimeBy = null;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setDateTimeBy(LocalDateTime dateTimeBy) {
        this.dateTimeBy = dateTimeBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
