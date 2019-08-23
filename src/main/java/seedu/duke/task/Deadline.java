package seedu.duke.task;

import seedu.duke.exceptions.DateParseException;

import java.text.ParseException;
import java.util.Date;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    public static final String REGEX = "/by";
    public static final String INITIAL = "D";

    private Date by;

    /**
     * Constructor.
     * @param desc The description of the deadline.
     * @param by The date of the deadline as a string.
     * @throws DateParseException If <code>by</code> cannot be parsed to a Date.
     */
    public Deadline(String desc, String by) throws DateParseException {
        super(desc.trim());
        try {
            this.by = INPUT_DATE_FORMAT.parse(by);
        } catch (ParseException e) {
            throw new DateParseException();
        }
    }

    @Override
    String getInitial() {
        return INITIAL;
    }

    @Override
    Date getDate() {
        return by;
    }

    @Override
    String getDatePrefix() {
        return "by: ";
    }
}
