import java.util.Date;

public class Deadline extends Task {

    private Date by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parse(by);
    }

    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String encode() {
        return "deadline," + super.description + "," + super.isDone + "," + DateFormatter.format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

}
