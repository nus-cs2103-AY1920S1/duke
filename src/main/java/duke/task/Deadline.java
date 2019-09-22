package duke.task;

import duke.exception.DukeException;
import duke.tag.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Represents a Task which has a deadline to adhere to.
 */
public class Deadline extends Task {
    /** String which represents the deadline of the task. */
    protected String by;

    /** Date object which represents the deadline of the task. */
    protected Date dateBy;

    /** Formatter object to check if format of date entered is correct. */
    protected static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /** Formatter object to convert the Date into a standard format. */
    protected static SimpleDateFormat stringFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm");

    private static final String MESSAGE_INVALID_DATE = "Invalid Date entered.";

    private static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid Date format entered : dd/mm/yyyy HHmm";

    /**
     * Initializes a Deadline Object with the description of the task and the deadline of the task.
     * This object is initialized as uncompleted.
     * @param description description of the task.
     * @param by deadline of the task.
     * @throws DukeException if deadline is of the wrong format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        checkValidBy();
    }

    /**
     * Initializes a Deadline Object with the description, deadline and tags of the task.
     * @param description description of the task.
     * @param by deadline of task.
     * @param tags tags tagged with tasks.
     * @throws DukeException if deadline is of the wrong format.
     */
    public Deadline(String description, String by, HashSet<Tag> tags) throws DukeException {
        super(description, tags);
        this.by = by;
        checkValidBy();
    }

    private void checkValidBy() throws DukeException {
        try {
            this.dateBy = formatter.parse(by);
            boolean isEquivDate = formatter.format(dateBy).equals(by);
            if (!isEquivDate) {
                throw new DukeException(MESSAGE_INVALID_DATE);
            }
        } catch (ParseException e) {
            throw new DukeException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Checks whether the task contains a certain word.
     * @param word word to search for.
     * @return whether task contains the word.
     */
    @Override
    public boolean hasWord(String word) {
        assert dateBy != null;
        String s = description + by;
        return s.toLowerCase().contains(word.toLowerCase());
    }

    /**
     * Returns a string representation to be used for saving data of this object.
     * @return String representation of this object for saving.
     */
    @Override
    public String getSaveData() {
        assert dateBy != null;
        String status = isDone ? "1" : "0";
        boolean isTagged = !tags.isEmpty();
        if (isTagged) {
            return "D \0 " + status + " \0 " + description + " \0 " + by + " \0 " + getTags();
        } else {
            return "D \0 " + status + " \0 " + description + " \0 " + by;
        }
    }

    /**
     * Returns a string representation of this object.
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        assert dateBy != null;
        boolean isTagged = !tags.isEmpty();
        if (isTagged) {
            return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + stringFormat.format(dateBy)
                    + ")" + " " + tags;
        } else {
            return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + stringFormat.format(dateBy) + ")";
        }
    }
}
