import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DukeDateFormatter.parse(by);
    }

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public String encode() {
        return "deadline," + super.description + "," + super.isDone + "," + DukeDateFormatter.format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

}
