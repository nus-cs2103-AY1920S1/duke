import java.util.Date;

public class Deadline extends Task {

    protected String by;

    protected Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        this.date = DateClass.stringToDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by);
    }

}
