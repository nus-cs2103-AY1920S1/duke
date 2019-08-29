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

    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string presentation of this task in hard disk.
     */
    public abstract String toStringForHardDisk();

    protected Date parseDate(String date) throws DukeException {
        try {
            return parser.parse(date);
        } catch (ParseException ex) {
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
