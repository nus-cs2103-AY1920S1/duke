import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date at;

    protected Event(String description, String at) throws DukeException {
        super(description);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date date = formatter.parse(at);
            this.at = date;
        } catch (ParseException e) {
            throw new DukeException("Failed to parse date.");
        }
    }
    protected Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toSaveFormat() {
        return String.format("E | %s | %s", super.toSaveFormat(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
