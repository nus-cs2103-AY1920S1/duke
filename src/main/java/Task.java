import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** A task. */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /** Constructor. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description
     *
     * @return The description.
     */
    public String getDesc() {
        return this.description;
    }

    /** Mark the task as done. */
    public void markDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return String of either a tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Parses string date into Date object according to our format.
     *
     * @param s Input string to be parsed.
     * @return The parsed Date object.
     */
    protected static Date parseDate(String s) throws DukeException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            return parser.parse(s);
        } catch (ParseException e) {
            throw new DukeException("Your date needs to be in dd/mm/yyyy hhmm format!");
        }
    }

    /**
     * Converts Date object into string of our format.
     *
     * @param d The Date object in question.
     * @return The string representation in our format.
     */
    protected static String stringifyDate(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:mm aaa");
        return formatter.format(d);
    }
}
