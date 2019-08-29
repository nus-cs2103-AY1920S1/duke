import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {
    protected static final SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String toStringForHardDisk();

    protected Date parseDate(String date) throws DukeException {
        try {
            return parser.parse(date);
        } catch(ParseException ex) {
            throw new DukeException("There is an error in parsing date.");
        }
    }

    protected String dateToStringForHardDisk(Date date) {
        return parser.format(date);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
