package exception;

public class DukeParseException extends DukeException {
    public DukeParseException() {
        super("OOPS!!! Please key in date and time in the format of <dd/M/yyyy hh:mm>");
    }
}
