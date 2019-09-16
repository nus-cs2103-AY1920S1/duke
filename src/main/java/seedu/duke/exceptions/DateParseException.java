package seedu.duke.exceptions;

public class DateParseException extends DukeException {

    public DateParseException() {
        super("Your date format is wrong. It must be in the form of dd/MM/yyyy HHmm");
    }
}
