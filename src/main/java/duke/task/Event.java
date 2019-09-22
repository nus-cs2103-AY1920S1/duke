package duke.task;

import duke.exception.DukeException;
import duke.tag.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Represents an Event.
 */
public class Event extends Task {
    /** String which represents the date and time of the event. */
    protected String at;

    /** Date object which represents the date and time of the event. */
    protected Date dateAt;

    /** Formatter object to format the date and time correctly. */
    protected static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /** Formatter object to convert the Date into a standard format. */
    protected static SimpleDateFormat stringFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm");

    private static final String MESSAGE_INVALID_DATE = "Invalid Date entered.";

    private static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid Date format entered : dd/mm/yyyy HHmm";

    /**
     * Initializes an Event Object with the description of the event and its date
     * and time.
     * This object is initialized as uncompleted.
     * @param description description of the event.
     * @param at date and time of the event.
     * @throws DukeException if date and time is of the wrong format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        checkValidAt();
    }

    /**
     * Initializes an Event Object with the description, date and time and tags.
     * @param description description of event.
     * @param at date and time of event.
     * @param tags tags tagged with event.
     * @throws DukeException if date and time is of wrong format.
     */
    public Event(String description, String at, HashSet<Tag> tags) throws DukeException {
        super(description, tags);
        this.at = at;
        checkValidAt();
    }

    private void checkValidAt() throws DukeException {
        try {
            this.dateAt = formatter.parse(at);
            boolean isEquivDate = formatter.format(dateAt).equals(at);
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
        assert dateAt != null;
        String s = description + at;
        return s.toLowerCase().contains(word.toLowerCase());
    }

    /**
     * Returns a string representation to be used for saving data of this object.
     * @return String representation of this object for saving.
     */
    @Override
    public String getSaveData() {
        assert dateAt != null;
        String status = isDone ? "1" : "0";
        boolean isTagged = !tags.isEmpty();
        if (isTagged) {
            return "E \0 " + status + " \0 " + description + " \0 " + at + " \0 " + getTags();
        } else {
            return "E \0 " + status + " \0 " + description + " \0 " + at;
        }
    }

    /**
     * Returns a string representation of this object.
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        assert dateAt != null;
        boolean isTagged = !tags.isEmpty();
        if (isTagged) {
            return "[E]" + "[" + getStatusIcon() + "] " + description + " (at: " + stringFormat.format(dateAt)
                    + ")" + " " + tags;
        } else {
            return "[E]" + "[" + getStatusIcon() + "] " + description + " (at: " + stringFormat.format(dateAt)
                    + ")";
        }
    }
}
