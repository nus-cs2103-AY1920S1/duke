import java.util.Date;

public class Deadline extends Task {

    protected String by;
    protected Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = DateParser.dateParser(by);
        if (date != null) { this.by = date.toString(); }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getDetails() {return "D @ " + super.getDetails() + " @ " + by; }
}