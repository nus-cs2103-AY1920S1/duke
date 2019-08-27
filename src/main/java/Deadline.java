import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date by;

    protected Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date date = formatter.parse(by);
            this.by = date;
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        }
    }
    protected Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
