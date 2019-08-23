package seedu.duke.task;

import seedu.duke.exceptions.DateParseException;

import java.text.ParseException;
import java.util.Date;

/**
 * Represents an event task.
 */
public class Event extends Task {
    public static final String REGEX = "/at";
    public static final String INITIAL = "E";

    private Date at;

    /**
     * Constructor.
     * @param desc The description of the event.
     * @param at The date of the event as a string.
     * @throws DateParseException If <code>at</code> cannot be parsed to a Date.
     */
    public Event(String desc, String at) throws DateParseException {
        super(desc.trim());
        try {
            this.at = INPUT_DATE_FORMAT.parse(at);
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
        return at;
    }

    @Override
    String getDatePrefix() {
        return "at: ";
    }
}
