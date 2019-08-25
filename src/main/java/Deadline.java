import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = Duke.formatter.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.formatter.format(by) + ")";
    }
}
