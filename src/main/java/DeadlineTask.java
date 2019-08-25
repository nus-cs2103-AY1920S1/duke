import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineTask extends Task {

    protected Date by;

    /**
     * Creates a DeadlineTask with a given description and deadline.
     * @param description Task description
     * @param by Task deadline date in format of "dd/mm/yyyy hhmm" e.g. "31/12/2019 2359"
     */
    DeadlineTask(String description, String by) throws DukeInvalidDateException {
        super(description);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.by = format.parse(by);
        } catch (ParseException e) {
            throw new DukeInvalidDateException(by);
        }
    }

    @Override
    public String toFileString() {
        return "D\t" + (this.isDone ? "1\t" : "0\t") + this.description + "\t" + dateFormat.format(this.by) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}