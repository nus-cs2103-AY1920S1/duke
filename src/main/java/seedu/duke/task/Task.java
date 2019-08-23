package seedu.duke.task;

import seedu.duke.exceptions.DukeException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task.
 */
public abstract class Task {
    private static final String DELIMITER = " ` ";

    public static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd MMMM yyyy hh:mma");

    private String description;
    private boolean isDone;

    /**
     * Parses and returns the object representation of <code>str</code>.
     * @param str The string to be parsed.
     * @return The corresponding task.
     * @throws DukeException If the initial does not match any Task's subclasses' initials.
     */
    public static Task parse(String str) throws DukeException {
        String[] data = str.split(DELIMITER);
        String type = data[0];
        boolean isDone = Integer.parseInt(data[1]) == 1;
        String desc = data[2];

        Task task = null;
        switch (type) {
        case Todo.INITIAL:
            task = new Todo(desc);
            break;
        case Deadline.INITIAL:
            task = new Deadline(desc, data[3]);
            break;
        case Event.INITIAL:
            task = new Event(desc, data[3]);
            break;
        default:
            throw new IllegalArgumentException("Initial does not match any known initials");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a unique initial of the subtype of Task.
     * @return The initial.
     */
    abstract String getInitial();

    /**
     * Returns a date associated to the task.
     * @return The associated date if applicable, null otherwise.
     */
    Date getDate() {
        return null;
    }

    /**
     * Returns the prefix that describes the date, e.g. "by: ", "at: ".
     * @return The prefix if applicable, null otherwise
     */
    String getDatePrefix() {
        return null;
    }

    /**
     * Returns the status icon that describes <code>isDone</code>.
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of this task to be saved to the disk.
     * @return The string representation.
     */
    public String getStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append(getInitial());
        sb.append(DELIMITER);
        sb.append(isDone ? 1 : 0);
        sb.append(DELIMITER);
        sb.append(description);
        if (getDate() != null) {
            sb.append(DELIMITER);
            sb.append(INPUT_DATE_FORMAT.format(getDate()));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[" + getInitial() + "]" + "[" + getStatusIcon() + "] " + description + " "
                + (getDate() == null ? "" : "(" + getDatePrefix() + OUTPUT_DATE_FORMAT.format(getDate()) + ")");
    }
}