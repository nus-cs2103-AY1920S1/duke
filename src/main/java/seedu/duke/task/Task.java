package seedu.duke.task;

import seedu.duke.exceptions.DukeException;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {
    private static final String DELIMITER = " ` ";

    public static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd MMMM yyyy hh:mma");

    private String description;
    private boolean isDone;

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

    abstract String getInitial();

    Date getDate() {
        return null;
    }

    String getPrefix() {
        return null;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

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
                + (getDate() == null ? "" : brackets(getPrefix() + OUTPUT_DATE_FORMAT.format(getDate())));
    }

    private String brackets(String s) {
        return "(" + s + ")";
    }
}