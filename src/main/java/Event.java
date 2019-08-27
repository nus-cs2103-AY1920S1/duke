import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date at;

    protected Event(String description, String at) throws DukeException {
        super(description);
        this.at = Utilities.dateParser(at);
    }
    protected Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        this.at = Utilities.fullDateParser(at);
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
