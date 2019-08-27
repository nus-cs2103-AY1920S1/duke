package duke.exception;

/**
 * Signals that some incorrect input has tried to be passed as arguments in Duke.
 */
public class DukeUnknownInputException extends DukeException {
    public DukeUnknownInputException(String description) {
        super(description);
    }
}
