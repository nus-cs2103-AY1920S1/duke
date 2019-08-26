package exception;

/**
 * Responsible for problems occured during parsing a user input.
 * Mainly used for DateTime format parsing errors.
 */
public class DukeParseException extends DukeException {
    public DukeParseException() {
        super("OOPS!!! Please key in date and time in the format of <dd/M/yyyy hh:mm>");
    }
}
