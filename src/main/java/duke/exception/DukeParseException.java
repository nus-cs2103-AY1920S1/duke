package duke.exception;

/**
 * Thrown when Duke fails to parse a Command.
 */
public class DukeParseException extends DukeException {
    public DukeParseException(final String message) {
        super(message);
    }
}
