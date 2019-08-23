package seedu.duke.task;

import seedu.duke.exceptions.DateParseException;

import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {
    public static final String REGEX = "/by";
    public static final String INITIAL = "D";

    private Date by;

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
    String getPrefix() {
        return "by: ";
    }
}
