import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {
    /**
     * a deadline task.
     */
    private Date by;

    /**
     * constructor.
     * @param description of deadline.
     * @param by is the date and time of the deadline.
     * @throws ParseException when format of date and time is different from required.
     */
    Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = Duke.formatter.parse(by);
    }

    /**
     * date and time.
     * @return a string showing the date and time of deadline.
     */
    public String getBy() {
        return Duke.formatter.format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.formatter.format(by) + ")";
    }
}
